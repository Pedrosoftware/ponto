package sistemaponto

import org.joda.time.LocalDate

class Feriado {

    LocalDate data

    static constraints = {
        data nullable: false
    }
}
