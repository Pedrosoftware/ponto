package sistemaponto

import org.joda.time.LocalDate
import org.joda.time.LocalTime

class RegistroPonto {

    LocalDate dia
    LocalTime hora
    boolean isEntrada
    Funcionario funcionario

    static constraints = {
        dia nullable: false
        hora nullable: false
    }

    def beforeValidate() {
        hora = hora.withMillisOfSecond(0)
    }
}
