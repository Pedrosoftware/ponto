package entity

import org.joda.time.DateTimeConstants
import sistemaponto.Feriado
import sistemaponto.Funcionario
import sistemaponto.RegistroPonto

/**
 * Created by pedro on 07/08/17.
 */
class PreparadorHistorico {

    SeletorCargaHoraria seletorCargaHoraria
    DefinidorTipoHora definidorTipoHora

    Historico preparar(long idFuncionario, int mesReferencia, int anoReferencia) {
        Historico historico = new Historico()
        historico.funcionario = Funcionario.findById(idFuncionario)
        historico.dias = SeletorMes.getDiasDoMes(mesReferencia, anoReferencia)
        definirValoresDiarios(historico.dias, historico.funcionario)
        definirValoresMensais(historico)
        return historico
    }

    private void definirValoresDiarios(List<Dia> dias, Funcionario funcionario) {
        for (dia in dias) {
            dia.isFeriado = Feriado.findByData(dia.data) != null
            dia.cargaTotalDia = seletorCargaHoraria.getCargaDoDia(dia.data, funcionario.cargaHoraria, dia.isFeriado)
            dia.pontos = RegistroPonto.findAllByFuncionarioAndDia(funcionario, dia.data)
            dia.isUtil = (!dia.isFeriado && dia.cargaTotalDia.getStandardMinutes() > 0)
            dia.isDomingo = (dia.data.getDayOfWeek() == DateTimeConstants.SUNDAY)
            dia.cargaCumprida = CalculadorHorariosDeTrabalho.calcular(dia.pontos*.hora)
            dia.tipoHora = definidorTipoHora.getTipo(dia)
        }
    }

    private static void definirValoresMensais(Historico historico) {
        for (dia in historico.dias) {
            historico.horasTrabalhadas += dia.cargaCumprida
            historico.horasCargaHoraria += dia.cargaTotalDia
            historico.horasFaltantes += dia.getHorasFaltantes()
            if (dia.tipoHora == TipoHoraExtra.HORA50) {
                historico.horasExtras50 += dia.getHorasExtrasCumpridas()
            } else if (dia.tipoHora == TipoHoraExtra.HORA100) {
                historico.horasExtras100 += dia.getHorasExtrasCumpridas()
            }
        }
    }
}
