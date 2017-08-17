package entity

import org.joda.time.Duration
import sistemaponto.Funcionario

/**
 * Created by pedro on 09/08/17.
 */
interface CalculadorSalario {
    Salario calcular(Funcionario funcionario,
                     Duration horasTrabalhadas,
                     Duration horasCargaHoraria,
                     Duration horasExtras50,
                     Duration horasExtras100)
}