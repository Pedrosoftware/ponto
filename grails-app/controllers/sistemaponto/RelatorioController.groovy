package sistemaponto

import service.ConfiguracaoService
import entity.Historico
import entity.Salario
import grails.plugin.springsecurity.annotation.Secured
import org.joda.time.LocalDate

@Secured(['ROLE_ADMIN'])
class RelatorioController {

    RelatorioService relatorioService
    FuncionarioService funcionarioService

    static defaultAction = "home"

    @Secured(['ROLE_USER'])
    def relatorio() {
        Map model = [:]
        LocalDate dataInformada

        if (params.mesSelecionado) {
            LocalDate hoje = new LocalDate()
            dataInformada = LocalDate.fromDateFields(params.mesSelecionado as Date).withDayOfMonth(hoje.getDayOfMonth())

            if (dataInformada > hoje) {
                model.mesSelecionado = dataInformada.toDate()
                model.msg = "O mês informado está no futuro"
                return render(view: '/home/registros', model: model)
            }

            model.relatorio = relatorioService.criar(
                    params.mesSelecionado_month as int, params.mesSelecionado_year as int)
        } else {
            model.relatorio = relatorioService.criar()
            dataInformada = ConfiguracaoService.getUltimoDiaFechamento()
        }
        if (!model.relatorio) {
            model.msg = "Não existem registros referêntes a este mês pois o funcionário ainda não tinha entrado na empresa"
        }
        model.mesSelecionado = dataInformada.toDate()
        render(view: '/home/registros', model: model)
    }

    def relatorioPonto() {
        Map model = [:]
        LocalDate mesSelecionado = new LocalDate()
        model.mesSelecionado = mesSelecionado.toDate()
        model.funcionarios = funcionarioService.listar()
        if (!params.idFuncionario) {
            model.msg = "Funcionário não informado"
            render(view: 'relatorioPonto', model: model)
            return
        }
        if (!params.mesSelecionado) {
            model.msg = "Mês de referência não informado"
            render(view: 'relatorioPonto', model: model)
            return
        }
        model.funcionario = funcionarioService.get(params.idFuncionario as int)
        mesSelecionado = LocalDate.fromDateFields(params.mesSelecionado as Date)
        Historico historico = relatorioService.criar(model.funcionario.id as int, mesSelecionado.getMonthOfYear(), mesSelecionado.getYear())
        if (historico) {
            if (historico.dias.size()) {
                model.relatorio = historico
            } else {
                model.msg = "Não existem pontos registrados para o mês informado"
            }
        } else {
            model.msg = "Mês anterior a data de contratação do funcionário"
        }
        render(view: 'relatorioPonto', model: model)
    }

    def relatorioSalario() {
        Map model = [:]
        if (!params.mesSelecionado) {
            return encaminhar("Selecione um mês para consulta", model)
        }

        LocalDate mesAtual = ConfiguracaoService.getDiaFechamento()
        LocalDate mesSelecionado = new LocalDate(params.mesSelecionado_year as int, params.mesSelecionado_month as int, mesAtual.getDayOfMonth())

        if (mesSelecionado > mesAtual) {
            return encaminhar("O mês informado ainda não aconteceu", model)
        } else if (mesSelecionado == mesAtual) {
            return encaminhar("O mês informado ainda não está fechado", model)
        }

        List<Salario> salarios = relatorioService.criarRelatorioSalarial(mesSelecionado.getMonthOfYear(), mesSelecionado.getYear())
        if (salarios.size()) {
            model.salarios = salarios
        } else {
            model.msg = "Não existem funcionários cadastrados no período informado"
        }
        model.mesSelecionado = mesSelecionado.toDate()
        render(view: 'relatorioSalario', model: model)
    }

    private def encaminhar(String msg, Map model) {
        model.msg = msg
        return render(view: 'relatorioSalario', model: model)
    }
}
