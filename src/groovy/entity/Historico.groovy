package entity

import org.joda.time.Duration
import sistemaponto.Funcionario

class Historico {

    Funcionario funcionario
    List<Dia> dias
    Duration horasTrabalhadas
    Duration horasCargaHoraria
    Duration horasExtras50
    Duration horasExtras100
    Duration horasFaltantes

    Historico(){
        horasTrabalhadas = new Duration(0)
        horasCargaHoraria = new Duration(0)
        horasExtras50 = new Duration(0)
        horasExtras100 = new Duration(0)
        horasFaltantes = new Duration(0)
    }
}
