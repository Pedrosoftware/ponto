package sistemaponto

import org.joda.time.LocalTime

class RequisicaoHorario {

    LocalTime horario

    static belongsTo = [requisicao: Requisicao]

    static constraints = {
        horario nullable: false
    }
}
