package entity

import org.joda.time.LocalDate
import service.ConfiguracaoService

/**
 * Created by pedro on 07/08/17.
 */
class SeletorMes {

    static List<Dia> getDiasDoMes(int mes, int ano) {
        LocalDate dataFimMes = getLastDayOfMonth(mes, ano)
        LocalDate dataInicioMes = dataFimMes.minusMonths(1).plusDays(1)
        List<Dia> lista = []
        Dia dia
        LocalDate localDate = dataInicioMes
        LocalDate hoje = new LocalDate()
        int contador = 0
        while (localDate.compareTo(dataFimMes) <= 0 && localDate.compareTo(hoje) <= 0) {
            dia = new Dia()
            dia.data = localDate
            lista << dia
            contador++
            localDate = dataInicioMes.plusDays(contador)
        }
        return lista
    }

    static LocalDate getLastDayOfMonth(int mes, ano){
        int diaFechamentoMes = ConfiguracaoService.getConfig(ConfiguracaoService.DIA_FECHAMENTO) as int
       return new LocalDate()
                .withYear(ano)
                .withMonthOfYear(mes)
                .withDayOfMonth(diaFechamentoMes)
    }

//    static LocalDate getFirstDayOfMonth(int mes, ano){
//        return getFirstDayOfMonth(mes, ano).minusMonths(1).plusDays(1)
//    }
}
