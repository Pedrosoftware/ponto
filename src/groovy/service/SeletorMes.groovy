package service

import entity.Dia
import org.joda.time.LocalDate

/**
 * Created by pedro on 07/08/17.
 */
class SeletorMes {

    static List<Dia> getDiasDoMes(int mes, int ano) {
        LocalDate dataFimMes = ConfiguracaoService.getDiaFechamento(mes, ano)
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
}
