package sistemaponto

import entity.CalculadorSalario
import entity.ConfiguracaoService
import entity.PreparadorHistorico
import entity.Historico
import entity.Salario
import entity.TipoHoraExtra
import grails.transaction.Transactional
import org.joda.time.Duration
import org.joda.time.LocalDate
import util.UtilitarioSpring

@Transactional
class RelatorioService {

    PreparadorHistorico preparadorHistorico
    CalculadorSalario calculadorSalario
    ConfiguracaoService configuracaoService
    FuncionarioService funcionarioService


    Historico criar(long idFuncionario) {
        LocalDate ld = new LocalDate()
        criar(idFuncionario, ld.getMonthOfYear(), ld.getYear())
    }

    Historico criar() {
        LocalDate ld = new LocalDate()
        Funcionario funcionario = UtilitarioSpring.getUsuarioLogado()
        criar(funcionario.id, ld.getMonthOfYear(), ld.getYear())
    }

    Historico criar(int mesDoRelatorio, int anoDoRelatorio) {
        Funcionario funcionario = UtilitarioSpring.getUsuarioLogado()
        criar(funcionario.id, mesDoRelatorio, anoDoRelatorio)
    }

    Historico criar(long idFuncionario, int mesDoRelatorio, int anoDoRelatorio) {
        if (funcJaTrabalhava(funcionarioService.get(idFuncionario as int).dataAdmissao, mesDoRelatorio, anoDoRelatorio)) {
            return preparadorHistorico.preparar(idFuncionario, mesDoRelatorio, anoDoRelatorio)
        }
        return null
    }

    List<Historico> criarParaTodosOsFuncionarios() {
        LocalDate hoje = new LocalDate()
        return criarParaTodosOsFuncionarios(hoje.getMonthOfYear(), hoje.getYear())
    }

    List<Historico> criarParaTodosOsFuncionarios(int mes, int ano) {
        List<Funcionario> funcionarios = funcionarioService.listar()
        List<Historico> historicos = []
        for (funcionario in funcionarios) {
            historicos << criar(funcionario.id, mes, ano)
        }
        return historicos
    }

    Duration totalHorasExtras(List<Historico> historicos) {
        Duration total = new Duration(0)
        for (historico in historicos) {
            total += historico.horasExtras50 + historico.horasExtras100
        }
        return total
    }

    Duration totalHorasExtras50(List<Historico> historicos) {
        Duration total = new Duration(0)
        for (historico in historicos) {
            total += historico.horasExtras50
        }
        return total
    }

    Duration totalHorasExtras100(List<Historico> historicos) {
        Duration total = new Duration(0)
        for (historico in historicos) {
            total += historico.horasExtras100
        }
        return total
    }

    List<Salario> criarRelatorioSalarial(int mesDoRelatorio, int anoDoRelatorio) {
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


    private boolean funcJaTrabalhava(LocalDate dataAdmissaoFuncionario, int mesDoRelatorio, int anoDoRelatorio) {
        LocalDate mesRelatorio = configuracaoService.getDiaFechamento(mesDoRelatorio, anoDoRelatorio)
        return dataAdmissaoFuncionario <= mesRelatorio

    }
}
