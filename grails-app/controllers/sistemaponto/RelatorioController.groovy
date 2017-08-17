package sistemaponto

import entity.Historico
import grails.plugin.springsecurity.annotation.Secured
import org.joda.time.LocalDate
import util.UtilitarioSpring

@Secured(['ROLE_ADMIN'])
class RelatorioController {

    RelatorioService relatorioService
    FuncionarioService funcionarioService

    static defaultAction = "home"

    def home() {

    }

    @Secured(['ROLE_USER', 'ROLE_ADMIN'])
    def relatorio() {
        Funcionario funcionario
        if (params.id) {
            funcionario = funcionarioService.get(params.id as int)
        } else {
            funcionario = UtilitarioSpring.getUsuarioLogado()
        }
        Historico relatorio = relatorioService.criar(funcionario.id)
        render(view: 'relatorio', model: ['relatorio': relatorio])
    }

    def relatorioPonto() {
        Map model = [:]
        LocalDate mesSelecionado = new LocalDate()
        if (params.idFuncionario) {
            model.funcionario = funcionarioService.get(params.idFuncionario as int)
            if (params.data_year && params.data_month) {
                mesSelecionado = new LocalDate(params.data_year as int, params.data_month as int, 1)
                model.relatorio = relatorioService.criar(params.idFuncionario as int, params.data_month as int, params.data_year as int)
            }
        }
        model.mesSelecionado = mesSelecionado.toDate()
        model.funcionarios = funcionarioService.listar()
        render(view: 'relatorioPonto', model: model)
    }

    def relatorioSalario() {
        //TODO validar parametros do mês e ano
        Map model = [:]
        LocalDate mesSelecionado = new LocalDate()
        if (params.data) {
            model.salarios = relatorioService.criarRelatorioSalarial(params.data_month as int, params.data_year as int)
            mesSelecionado = new LocalDate(params.data_year as int, params.data_month as int, 1)
        }
        model.mesSelecionado = mesSelecionado.toDate()
        render(view: 'relatorioSalario', model: model)
    }
}
