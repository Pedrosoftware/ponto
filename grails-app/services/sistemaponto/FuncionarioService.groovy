package sistemaponto

import entity.ConfiguracaoService
import grails.transaction.Transactional
import org.joda.time.LocalDate

@Transactional
class FuncionarioService {

    RegistroPontoService registroPontoService

    Funcionario salvar(Funcionario funcionario) {
        Map model = [:]
        model['msg'] = ""
        if (funcionario.save(flush: true)) {
            if (funcionario.isAdmin) {
                FuncionarioRegra.create(funcionario, Regra.findByAuthority('ROLE_ADMIN'), true)
            }
            FuncionarioRegra.create(funcionario, Regra.findByAuthority('ROLE_USER'), true)
        }else{
            if(funcionario.hasErrors()){
                funcionario.errors.allErrors.each { println it }
            }
        }
        return funcionario
    }

    List<Funcionario> listar() {
        List<Funcionario> lista = Funcionario.findAll()
        lista.sort { it.nome.toLowerCase() }
        return lista
    }

    Funcionario get(int id) {
        return Funcionario.get(id)
    }

    List<Funcionario> entraramNoMes() {
        LocalDate data = new LocalDate()
        return entraramNoMes(data.getMonthOfYear(), data.getYear())
    }

    List<Funcionario> entraramNoMes(int mes, int ano) {
        LocalDate aberturaMes = ConfiguracaoService.getDiaAbertura(mes, ano)
        LocalDate fechamentoMes = ConfiguracaoService.getDiaFechamento(mes, ano)
        return Funcionario.findAllByDataAdmissaoBetween(aberturaMes, fechamentoMes)
    }

    double salarioTotal() {
        LocalDate data = new LocalDate()
        return salarioTotalDoPeriodo(data.getMonthOfYear(), data.getYear())
    }

    double salarioTotalDoPeriodo(int mes, int ano) {
        LocalDate fechamentoMes = ConfiguracaoService.getDiaFechamento(mes, ano)
        List<Funcionario> lista = Funcionario.findAllByDataAdmissaoLessThan(fechamentoMes)
        double salario = 0
        for (funcionario in lista) {
            salario += funcionario.salario
        }
        return salario
    }


    List<Funcionario> trabalhandoNoMomento() {
        LocalDate hoje = new LocalDate()
        List<RegistroPonto> registros = registroPontoService.buscarPontosDoDia(hoje)
        Map<Funcionario, List<RegistroPonto>> mapa = registros.groupBy { it.funcionario }
        List<Funcionario> trabalhadores = []
        mapa.each {
            if (it.value.size()) {
                RegistroPonto rp = it.value.get(it.value.size() - 1)
                if (rp.isEntrada) {
                    trabalhadores << rp.funcionario
                }
            }
        }
        return trabalhadores
    }

}
