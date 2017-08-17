package entity

import sistemaponto.Funcionario

/**
 * Created by pedro on 09/08/17.
 */
class Salario {

    Funcionario funcionario
    double valorHora50
    double valorHora100
    double valorDescontado
    double valorBase

    Salario(){
        valorHora50 = 0
        valorHora100 = 0
        valorDescontado = 0
    }

    double getTotal(){
        return valorBase + valorHora50 + valorHora100 - valorDescontado
    }
}
