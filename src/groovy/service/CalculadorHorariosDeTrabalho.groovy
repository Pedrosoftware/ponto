package service

import org.joda.time.Duration
import org.joda.time.LocalTime
import org.joda.time.Period

class CalculadorHorariosDeTrabalho {

    static Duration calcular(List<LocalTime> pontos) {
        if (pontos.size() % 2 == 1) {
            //throw new Exception("Quantidade impar de pontos")
            pontos.remove(pontos.size()-1)
        }

        Duration totalEntrada = new Duration(0)
        Duration totalSaida = new Duration(0)
        LocalTime lt

        for (int i = 0; i < pontos.size(); i++) {
            lt = pontos.get(i)
            if (i % 2 == 0) {
                totalEntrada += new Period(lt.getHourOfDay(),
                        lt.getMinuteOfHour(),
                        lt.getSecondOfMinute(), 0).toStandardDuration()
            } else {
                totalSaida += new Period(lt.getHourOfDay(),
                        lt.getMinuteOfHour(),
                        lt.getSecondOfMinute(), 0).toStandardDuration()
            }
        }
        return totalSaida - totalEntrada
    }
}
