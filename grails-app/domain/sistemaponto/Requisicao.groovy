package sistemaponto

import org.joda.time.LocalDate

class Requisicao {

    LocalDate diaRequisitado
    LocalDate dataSolicitacao
    String justificativa
    boolean isFinalizada
    boolean isAprovada

    static hasMany = [horarios: RequisicaoHorario]
    static belongsTo = [funcionario: Funcionario]

    static constraints = {
        dataSolicitacao nullable: false
        justificativa nullable: false, blank: false, maxSize: 255
        horarios nullable: false

    }
}
