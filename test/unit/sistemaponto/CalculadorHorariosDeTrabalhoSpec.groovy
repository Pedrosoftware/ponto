package sistemaponto

import service.CalculadorHorariosDeTrabalho
import org.joda.time.Duration
import org.joda.time.LocalTime as lt
import org.junit.Test
import spock.lang.Specification
import spock.lang.Unroll

class CalculadorHorariosDeTrabalhoSpec extends Specification{

    @Unroll
    @Test
    def "total de horas trabalhadas em um dia"(){

        given: "uma lista de pontos qualquer"
            CalculadorHorariosDeTrabalho cct = new CalculadorHorariosDeTrabalho()
            List<lt> pontos = [ new lt(e1,00,00), new lt(s1,00,00), new lt(e2,00,00), new lt(s2,00,00)]
        expect: "quando executar o calculo o resultado seja o esperado"
            cct.calcular(pontos) == result
        where:
        e1 | s1 | e2 | s2 || result
        7  | 12 | 14 | 18 || new Duration(32400000)
        8  | 9  | 15 | 16 || new Duration(7200000)
        7  | 11 | 12 | 18 || new Duration(36000000)

    }
}
