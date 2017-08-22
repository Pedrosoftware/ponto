package service

import org.joda.time.DateTimeConstants
import org.joda.time.Duration
import org.joda.time.LocalDate
import sistemaponto.CargaHoraria

/**
 * Created by pedro on 07/08/17.
 */
class SeletorCargaHoraria {

    static Duration getCargaDoDia(LocalDate data, CargaHoraria cargaHoraria, boolean isFeriado) {
        Duration duration
        if (isFeriado) {
            duration = new Duration(0)
        } else {
            duration = getCarga(data, cargaHoraria)
        }
        return duration
    }

    private static Duration getCarga(LocalDate dia, CargaHoraria cargaHoraria) {
        int diaDaSemana = dia.getDayOfWeek()
        int cargaDoDia
        switch (diaDaSemana) {
            case DateTimeConstants.MONDAY:
                cargaDoDia = cargaHoraria.segunda
                break
            case DateTimeConstants.TUESDAY:
                cargaDoDia = cargaHoraria.terca
                break
            case DateTimeConstants.WEDNESDAY:
                cargaDoDia = cargaHoraria.quarta
                break
            case DateTimeConstants.THURSDAY:
                cargaDoDia = cargaHoraria.quinta
                break
            case DateTimeConstants.FRIDAY:
                cargaDoDia = cargaHoraria.sexta
                break
            case DateTimeConstants.SATURDAY:
                cargaDoDia = cargaHoraria.sabado
                break
            case DateTimeConstants.SUNDAY:
                cargaDoDia = cargaHoraria.domingo
                break
            default:
                throw new Exception("Dia da semana inválido")
        }
        return new Duration(cargaDoDia*60*60*1000)
    }

}
