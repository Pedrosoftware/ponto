package sistemaponto

import grails.plugin.springsecurity.annotation.Secured
import org.joda.time.LocalDate

@Secured(['ROLE_ADMIN'])
class FuncionarioController {

    FuncionarioService funcionarioService
    RelatorioService relatorioService

    static defaultAction = "homepadrao"

    @Secured(['ROLE_USER'])
    def homepadrao() {
        Map model = chainModel ? chainModel : [:]
        model['relatorio'] = relatorioService.criar()

        render(view: '/home/home', model: model)
    }

    def formulario() {
        Map model = [:]
        if (params.id) {
            model['funcionario'] = funcionarioService.get(params.id as int)
        }
        model['cargaHoraria'] = CargaHoraria.list()
        render(view: 'formulario', model: model)
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

        chain(controller: 'funcionario', action: 'listar', model: ['msg':"Funcionário cadastrado com sucesso"])
    }

    def listar() {
        Map model = chainModel ? chainModel : [:]
        model.funcionarios = funcionarioService.listar()
        return render(view: 'listar', model: model)
    }
}
