package sistemaponto

import service.ConfiguracaoService
import grails.plugin.springsecurity.annotation.Secured
import org.joda.time.LocalDate

@Secured(['ROLE_ADMIN'])
class FuncionarioController {

    FuncionarioService funcionarioService
    RegistroPontoService registroPontoService
    RelatorioService relatorioService

    static defaultAction = "homepadrao"

    def formulario() {
        Map model = [:]
        if (params.id) {
            model['funcionario'] = funcionarioService.get(params.id as int)
        }
        model['cargaHoraria'] = CargaHoraria.list()
        render(view: 'formulario', model: model)
    }

    @Secured(['ROLE_USER'])
    def homepadrao() {
        Map model = [:]
        model['relatorio'] = relatorioService.criar()
        if (params.msg) {
            model['msg'] = params.msg
        }
        if (chainModel) {
            model += chainModel
        }

        render(view: '/home/home', model: model)
    }

    def salvar(Funcionario funcionario) {
        try {
            funcionario.dataAdmissao = LocalDate.fromDateFields(params.dataAdmissao as Date)
        } catch (Exception ex) {
            println "FuncionarioController.cadastro(funcionario) -> não deu certo a data\n${ex}"
        }
        funcionario.salario = params.funcionario.salario as double
        funcionario = funcionarioService.salvar(funcionario)
        if (!funcionario) {
            Map model = [:]
            model.msg = 'Falha ao salvar funcionário'
            model.cargaHoraria = CargaHoraria.list()
            render(view: 'formulario', model: model)
            return
        }

        chain(controller: 'funcionario', action: 'listar', params: ['msg':"Funcionário cadastrado com sucesso"])
    }

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

    def listar() {
        Map model = [:]
        if(params.msg){
            model.msg = params.msg
        }
        model.funcionarios = funcionarioService.listar()
        return render(view: 'listar', model: model)
    }
}
