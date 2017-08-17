package sistemaponto

import entity.CalculadorSalario
import entity.CalculadorSalarioHoraPorHora
import entity.Salario
import org.joda.time.Duration
import org.junit.Test
import spock.lang.Specification
import spock.lang.Unroll

class CalculadorSalarioHoraPorHoraSpec extends Specification{

    @Unroll
    @Test
    def "checagem do calculo do salário"(){

        given:
            Funcionario funcionario = new Funcionario()
            Duration horasTrabalhadas
            Duration horasCargaHoraria
            Duration horasExtras50
            Duration horasExtras100
            Salario salario
            CalculadorSalario calculadorSalario = new CalculadorSalarioHoraPorHora()
        when:
            funcionario.salario = salarioBase
            horasTrabalhadas = new Duration(hrsCumpridas*60*60*1000)
            horasCargaHoraria = new Duration(cargaHoraria*60*60*1000)
            horasExtras50 = new Duration(horaExtra50*60*60*1000)
            horasExtras100 = new Duration(horaExtra100*60*60*1000)
            salario = calculadorSalario.calcular(funcionario,horasTrabalhadas, horasCargaHoraria, horasExtras50, horasExtras100)
        then:
            salario.getTotal() == salarioFinal
            salario.valorDescontado == descontos
            salario.valorHora50 == hora50
            salario.valorHora100 == hora100
        where:
        salarioBase | hrsCumpridas | cargaHoraria | horaExtra50 | horaExtra100 | salarioFinal | hora50 | hora100 | descontos
        100         | 200          | 100          | 100         | 0            | 250          | 150    | 0       | 0
        100         | 200          | 100          | 0           | 100          | 300          | 0      | 200     | 0
        100         | 80           | 100          | 0           | 0            | 80           | 0      | 0       | 20
        100         | 100          | 100          | 10          | 10           | 100          | 0      | 0       | 0
    }
}
