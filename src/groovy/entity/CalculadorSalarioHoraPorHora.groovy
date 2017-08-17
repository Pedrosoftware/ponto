package entity

import org.joda.time.Duration
import sistemaponto.Funcionario

/**
 * Created by pedro on 08/08/17.
 */
class CalculadorSalarioHoraPorHora implements CalculadorSalario{

    @Override
    Salario calcular(Funcionario funcionario, Duration horasTrabalhadas, Duration horasCargaHoraria, Duration horasExtras50, Duration horasExtras100) {

        double precoHoraPorMinuto = (funcionario.salario / horasCargaHoraria.getStandardMinutes())
        Duration diferenca = horasTrabalhadas - horasCargaHoraria
        Salario salario
        switch (diferenca.getStandardMinutes()){
            case {it < 0}:
                salario = salarioDescontado(horasCargaHoraria, horasTrabalhadas, precoHoraPorMinuto)
                break
            case {it > 0}:
                salario =  salarioAcumulado(horasExtras50, horasExtras100,precoHoraPorMinuto)
                break
            default:
                salario = new Salario()
        }
        salario.funcionario = funcionario
        salario.valorBase = funcionario.salario
        return salario
    }

    private static Salario salarioDescontado(Duration horasCargaHoraria, Duration horasTrabalhadas, double precoHoraPorMinuto) {
        Salario s = new Salario()
        Duration horasDevedouras = horasCargaHoraria - horasTrabalhadas
        s.valorDescontado = horasDevedouras.getStandardMinutes() * precoHoraPorMinuto
        return s
    }

    private static Salario salarioAcumulado(Duration horasExtras50, Duration horasExtras100, double precoHoraPorMinuto) {
        Salario s = new Salario()
        s.valorHora50 = horasExtras50.getStandardMinutes()*getHoras50(precoHoraPorMinuto)
        s.valorHora100 = horasExtras100.getStandardMinutes()*getHoras100(precoHoraPorMinuto)
        return s
    }

    private static double getHoras50(double precoHoraPorMinuto){
        return precoHoraPorMinuto/2*3
    }
    private static double getHoras100(double precoHoraPorMinuto){
        return precoHoraPorMinuto*2
    }
}
