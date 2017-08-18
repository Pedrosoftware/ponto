import entity.Permissao
import org.joda.time.LocalDate
import org.joda.time.LocalTime
import sistemaponto.CargaHoraria
import entity.ConfiguracaoService
import sistemaponto.Feriado
import sistemaponto.Funcionario
import sistemaponto.RegistroPontoService
import sistemaponto.Regra
import sistemaponto.FuncionarioRegra

class BootStrap {

    RegistroPontoService registroPontoService
    def init = { servletContext ->
        int diaFechamento = 25
        ConfiguracaoService.escrever(ConfiguracaoService.DIA_FECHAMENTO, String.valueOf(diaFechamento))

        sistemaponto.CargaHoraria.findOrSaveByDescricaoAndSegundaAndTercaAndQuartaAndQuintaAndSextaAndSabadoAndDomingo('padrão', 8, 8, 8, 8, 8, 5, 0)
        sistemaponto.CargaHoraria.findOrSaveByDescricaoAndSegundaAndTercaAndQuartaAndQuintaAndSextaAndSabadoAndDomingo('normal', 8, 8, 8, 8, 8, 8, 8)
        sistemaponto.CargaHoraria.findOrSaveByDescricaoAndSegundaAndTercaAndQuartaAndQuintaAndSextaAndSabadoAndDomingo('exceptional', 4, 4, 4, 4, 4, 0, 0)

        Funcionario preguicoso = Funcionario.findOrSaveByUsernameAndPasswordAndIsAdminAndSalarioAndCargaHorariaAndNomeAndDataAdmissao("pedro", "fibo@123", true, 1000, CargaHoraria.findById(1), "Funcionario Preguiçoso", new LocalDate(2017, 1, diaFechamento + 1))
        Funcionario pontual = Funcionario.findOrSaveByUsernameAndPasswordAndIsAdminAndSalarioAndCargaHorariaAndNomeAndDataAdmissao("paulo", "fibo@123", false, 2000, CargaHoraria.findById(2), "Funcionario Pontual", new LocalDate(2017, 1, diaFechamento + 1))
        Funcionario dedicado = Funcionario.findOrSaveByUsernameAndPasswordAndIsAdminAndSalarioAndCargaHorariaAndNomeAndDataAdmissao("pablo", "fibo@123", false, 3000, CargaHoraria.findById(1), "Funcionario Dedicado", new LocalDate(2017, 1, diaFechamento + 1))


        Regra regraAdmin = new Regra('ROLE_ADMIN')
        Regra regraUser = new Regra('ROLE_USER')
        regraAdmin.save()
        regraUser.save()

        FuncionarioRegra.create(preguicoso, regraAdmin, true)
        FuncionarioRegra.create(pontual, regraUser, true)

        assert (Funcionario.count() == 3)
        assert (Regra.count() == 2)
        assert FuncionarioRegra.count() == 2

        List funcionarios = [preguicoso, pontual, dedicado]

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
                [[preguicoso]: pontosBatidos.preguicoso, [pontual]: pontosBatidos.pontual, [dedicado]: pontosBatidos.dedicado]

        LocalDate dia = new LocalDate(2017, 6, diaFechamento).plusDays(1)
        LocalDate fechamentoMes = new LocalDate()
        while (dia <= fechamentoMes) {
            for (item in mapa) {
                for (ponto in item.value) {
                    //sistemaponto.RegistroPonto.findOrSaveByDiaAndHoraAndFuncionarioAndIsEntrada(dia, ponto, item.key, true)
                    registroPontoService.registrar(item.key.get(0), dia, ponto)
                }
            }
            dia = dia.plusDays(1)
        }
        Feriado.findOrSaveByData(new LocalDate(2017, 7, 10))
        Feriado.findOrSaveByData(new LocalDate(2017, 7, 18))
    }

    def destroy = {
    }
}
