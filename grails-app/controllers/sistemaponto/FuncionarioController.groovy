package sistemaponto

import grails.plugin.springsecurity.annotation.Secured
import org.joda.time.LocalDate

class FuncionarioController {

    FuncionarioService funcionarioService

    static defaultAction = "home"

    @Secured(['ROLE_ADMIN'])
    def formulario() {
        Map model = [:]
        model['cargaHoraria'] = CargaHoraria.list()
        model['funcionario'] = simulaFuncionario()
        return model
    }

    @Secured(['ROLE_USER', 'ROLE_ADMIN'])
    def home() {
        return render (view: 'home')
    }

    @Secured(['ROLE_USER', 'ROLE_ADMIN'])
    def homepadrao() {
        return render (view: '/home/home')
    }

    @Secured(['ROLE_ADMIN'])
    def cadastro(Funcionario funcionario) {
        funcionario.dataAdmissao = LocalDate.fromDateFields(params.dataAdmissao as Date)
        Map model = funcionarioService.salvar(funcionario)
        FuncionarioRegra.create(funcionario, Regra.findByAuthority('ROLE_USER'))
        model['cargaHoraria'] = CargaHoraria.list()
        return render(view: 'formulario', model: model)
    }

    @Secured(['ROLE_ADMIN'])
    def listar(){
        return render(view: 'listar', model:['funcionarios':funcionarioService.listar()])
    }

    private static Funcionario simulaFuncionario(){
        Funcionario f = new Funcionario()
        f.nome = "Fulano"
        f.username = new Random().nextInt()+""
        f.password = "fibo@123"
        f.salario = 5000
        f.isAdmin = true
        f.cargaHoraria = CargaHoraria.get(1)
        return f
    }
}
