package sistemaponto

import entity.CalculadorSalario
import entity.ConfiguracaoService
import entity.PreparadorHistorico
import entity.Historico
import entity.Salario
import grails.transaction.Transactional
import org.joda.time.LocalDate
import util.UtilitarioSpring

@Transactional
class RelatorioService {

    PreparadorHistorico preparadorHistorico
    CalculadorSalario calculadorSalario
    ConfiguracaoService configuracaoService


    Historico criar(long idFuncionario) {
        LocalDate ld = new LocalDate()
        criar(idFuncionario, ld.getMonthOfYear(), ld.getYear())
    }

    Historico criar() {
        LocalDate ld = new LocalDate()
        Funcionario funcionario = UtilitarioSpring.getUsuarioLogado()
        criar(funcionario.id, ld.getMonthOfYear(), ld.getYear())
    }

    Historico criar(long idFuncionario, int mesDoRelatorio, int anoDoRelatorio) {
        return preparadorHistorico.preparar(idFuncionario, mesDoRelatorio, anoDoRelatorio)
    }

    List<Salario> criarRelatorioSalarial(int mesDoRelatorio, int anoDoRelatorio) {
        //TODO checar se o mês já fechou, pois não é possível gerar salários de um mês que não fechou
        LocalDate lastDayOfMonth = new LocalDate().withYear(anoDoRelatorio).withMonthOfYear(mesDoRelatorio)
                .withDayOfMonth(Integer.parseInt(configuracaoService.getConfig(ConfiguracaoService.DIA_FECHAMENTO)))
        List<Salario> salarios = []
        Historico relatorio
        for (funcionario in Funcionario.findAllByDataAdmissaoLessThanEquals(lastDayOfMonth)) {
            relatorio = preparadorHistorico.preparar(funcionario.id, lastDayOfMonth.getMonthOfYear(), lastDayOfMonth.getYear())

            salarios << calculadorSalario.calcular(
                    funcionario,
                    relatorio.horasTrabalhadas,
                    relatorio.horasCargaHoraria,
                    relatorio.horasExtras50,
                    relatorio.horasExtras100)
        }
        return salarios
    }
}
