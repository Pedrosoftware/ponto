import org.joda.time.LocalDate
import org.joda.time.LocalTime
import sistemaponto.CargaHoraria
import service.ConfiguracaoService
import sistemaponto.Feriado
import sistemaponto.Funcionario
import sistemaponto.FuncionarioService
import sistemaponto.RegistroPontoService
import sistemaponto.Regra
import sistemaponto.Requisicao
import sistemaponto.RequisicaoHorario
import sistemaponto.RequisicaoService

class BootStrap {

    RegistroPontoService registroPontoService
    FuncionarioService funcionarioService

    def init = { servletContext ->
        criarRegrasDeAcesso()
        criarCargasHorarias()
        configurarDiaFechamentoDoMes()
        List<Funcionario> funcionarios = cadastrarFuncionarios()
        baterPontos(funcionarios)
        cadastroDeFeriados()
    }

    List<Funcionario> cadastrarFuncionarios() {
        int diaFechamento = Integer.parseInt(ConfiguracaoService.getConfig(ConfiguracaoService.DIA_FECHAMENTO))
        List<Funcionario> funcionarios = []
        funcionarios << new Funcionario(
                nome: "John Oliveira",
                username: "john",
                password: "fibo@123",
                isAdmin: false,
                salario: 1000,
                cargaHoraria: CargaHoraria.findById(3),
                dataAdmissao: new LocalDate(2017, 1, diaFechamento + 1))

        funcionarios << new Funcionario(
                nome: "Paulo Oliveira",
                username: "paulo",
                password: "fibo@123",
                isAdmin: false,
                salario: 1500,
                cargaHoraria: CargaHoraria.findById(4),
                dataAdmissao: new LocalDate(2017, 1, diaFechamento + 1))

        funcionarios << new Funcionario(
                nome: "Pedro Henrique",
                username: "pedro",
                password: "fibo@123",
                isAdmin: true,
                salario: 2000,
                cargaHoraria: CargaHoraria.findById(3),
                dataAdmissao: new LocalDate(2017, 1, diaFechamento + 1))

        funcionarios << new Funcionario(
                nome: "Gustavo Alves",
                username: "gustavo",
                password: "fibo@123",
                isAdmin: false,
                salario: 2500,
                cargaHoraria: CargaHoraria.findById(3),
                dataAdmissao: new LocalDate().minusDays(5))

        for (funcionario in funcionarios) {
            funcionarioService.salvar(funcionario)
        }
        return funcionarios
    }

    void baterPontos(List<Funcionario> funcs) {
        LocalTime hora07 = new LocalTime(7, 0, 0)
        LocalTime hora08 = new LocalTime(8, 0, 0)
        LocalTime hora10 = new LocalTime(10, 0, 0)
        LocalTime hora12 = new LocalTime(12, 0, 0)
        LocalTime hora14 = new LocalTime(14, 0, 0)
        LocalTime hora15 = new LocalTime(15, 0, 0)
        LocalTime hora18 = new LocalTime(18, 0, 0)

        Map<String, List<LocalTime>> pontosBatidos = [:]
        pontosBatidos.preguicoso = [hora08, hora10, hora12, hora15]  //5 horas trabalhadas
        pontosBatidos.pontual = [hora08, hora12, hora14, hora18]     //8 horas trabalhadas
        pontosBatidos.dedicado = [hora07, hora12, hora14, hora18]    //9 horas trabalhadas

        Map<Funcionario, List<LocalTime>> mapa =
                [[funcs.get(0)]: pontosBatidos.preguicoso, [funcs.get(1)]: pontosBatidos.pontual, [funcs.get(2)]: pontosBatidos.dedicado]

        LocalDate dia = funcs.get(0).dataAdmissao.plusDays(10)
        LocalDate fechamentoMes = new LocalDate()
        while (dia <= fechamentoMes) {
            for (item in mapa) {
                for (ponto in item.value) {
                    registroPontoService.registrar(item.key.get(0), dia, ponto)
                }
            }
            dia = dia.plusDays(1)
        }
    }

    void cadastroDeFeriados() {
        LocalDate hoje = new LocalDate()
        Feriado.findOrSaveByData(hoje.minusDays(5))
        Feriado.findOrSaveByData(hoje.minusDays(10))
        Feriado.findOrSaveByData(hoje.minusDays(15))
    }

    void criarRegrasDeAcesso() {
        Regra regraAdmin = new Regra('ROLE_ADMIN')
        Regra regraUser = new Regra('ROLE_USER')
        regraAdmin.save()
        regraUser.save()
    }

    int configurarDiaFechamentoDoMes() {
        int diaFechamento = 25
        ConfiguracaoService.escrever(ConfiguracaoService.DIA_FECHAMENTO, String.valueOf(diaFechamento))
        return diaFechamento
    }

    void criarCargasHorarias() {

        sistemaponto.CargaHoraria.findOrSaveByDescricaoAndSegundaAndTercaAndQuartaAndQuintaAndSextaAndSabadoAndDomingo('padrão', 8, 8, 8, 8, 8, 5, 0)
        sistemaponto.CargaHoraria.findOrSaveByDescricaoAndSegundaAndTercaAndQuartaAndQuintaAndSextaAndSabadoAndDomingo('normal', 8, 8, 8, 8, 8, 8, 8)
        sistemaponto.CargaHoraria.findOrSaveByDescricaoAndSegundaAndTercaAndQuartaAndQuintaAndSextaAndSabadoAndDomingo('exceptional', 4, 4, 4, 4, 4, 0, 0)

    }

    def destroy = {
    }


//    void requisicaoDePontos(List<Funcionario> funcionarios) {
//        LocalTime hora08 = new LocalTime(8, 0, 0)
//        LocalTime hora10 = new LocalTime(10, 0, 0)
//        LocalTime hora14 = new LocalTime(14, 0, 0)
//        LocalTime hora18 = new LocalTime(18, 0, 0)
//
//        LocalDate dataSolicitacao = new LocalDate()
//        LocalDate diaRequisitado = dataSolicitacao.minusDays(5)
//
//        List<RequisicaoHorario> horarios = [new RequisicaoHorario(horario: hora08),
//                                            new RequisicaoHorario(horario: hora10),
//                                            new RequisicaoHorario(horario: hora14),
//                                            new RequisicaoHorario(horario: hora18)]
//
//        for (funcionario in funcionarios) {
//            Requisicao requisicao = new Requisicao(dataSolicitacao: dataSolicitacao,
//                    diaRequisitado: diaRequisitado,
//                    justificativa: "Esqueci de bater o ponto neste dia por algum motivo estranho",
//                    funcionario: funcionario)
//            for(reqHorario in horarios){
//                requisicao.addToHorarios(reqHorario)
//            }
//            requisicaoService.criarRequisicao(requisicao)
//        }
//
//        diaRequisitado = diaRequisitado.withDayOfMonth(10)
//        for (funcionario in funcionarios) {
//            Requisicao requisicao = new Requisicao(dataSolicitacao: dataSolicitacao,
//                    diaRequisitado: diaRequisitado,
//                    justificativa: "Esqueci de bater o ponto neste dia por algum motivo estranho",
//                    funcionario: funcionario)
//            for(reqHorario in horarios){
//                requisicao.addToHorarios(reqHorario)
//            }
//            requisicaoService.criarRequisicao(requisicao)
//            requisicaoService.finalizar(false, requisicao.id as int)
//        }
//    }
}
