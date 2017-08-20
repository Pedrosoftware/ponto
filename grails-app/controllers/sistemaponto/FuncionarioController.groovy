package sistemaponto

import entity.ConfiguracaoService
import grails.plugin.springsecurity.annotation.Secured
import org.joda.time.LocalDate

class FuncionarioController {

    FuncionarioService funcionarioService
    RegistroPontoService registroPontoService
    RelatorioService relatorioService

    static defaultAction = "homepadrao"

    @Secured(['ROLE_ADMIN'])
    def formulario() {
        Map model = [:]
        model['cargaHoraria'] = CargaHoraria.list()
        model['funcionario'] = simulaFuncionario()
        return model
    }

    @Secured(['ROLE_USER', 'ROLE_ADMIN'])
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

    @Secured(['ROLE_USER', 'ROLE_ADMIN'])
    def baterPonto() {
        Map model = [:]
        if (registroPontoService.registrar()) {
            model['msg'] = "Ponto registrado"
            return redirect(controller: 'funcionario', action: 'homepadrao', params: model)
        }
        model['msg'] = "Falha ao registrar o ponto"
        redirect(controller: 'funcionario', action: 'homepadrao', params: model)
    }

    @Secured(['ROLE_ADMIN'])
    def cadastro(Funcionario funcionario) {
        funcionario.dataAdmissao = LocalDate.fromDateFields(params.dataAdmissao as Date)
        Map model = funcionarioService.salvar(funcionario)
        FuncionarioRegra.create(funcionario, Regra.findByAuthority('ROLE_USER'))
        model['cargaHoraria'] = CargaHoraria.list()
        render(view: 'formulario', model: model)
    }

    @Secured(['ROLE_USER', 'ROLE_ADMIN'])
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
            dataInformada = ConfiguracaoService.getDiaFechamento()
        }
        if (!model.relatorio) {
            model.msg = "Não existem registros referêntes a este mês pois o funcionário ainda não tinha entrado na empresa"
        }
        model.mesSelecionado = dataInformada.toDate()
        render(view: '/home/registros', model: model)
    }

    @Secured(['ROLE_ADMIN'])
    def listar() {
        return render(view: 'listar', model: ['funcionarios': funcionarioService.listar()])
    }

    private static Funcionario simulaFuncionario() {
        Funcionario f = new Funcionario()
        f.nome = "Fulano"
        f.username = new Random().nextInt() + ""
        f.password = "fibo@123"
        f.salario = 5000
        f.isAdmin = true
        f.cargaHoraria = CargaHoraria.get(1)
        return f
    }
}
