package sistemaponto

import entity.ConfiguracaoService
import grails.gorm.DetachedCriteria
import grails.transaction.Transactional
import org.joda.time.LocalDate

@Transactional
class RequisicaoService {

    RegistroPontoService registroPontoService

    List<Requisicao> listarEmAberto() {
        DetachedCriteria emAberto = Requisicao.where {
            isFinalizada == false
        }
        return emAberto.list(sort: 'dataSolicitacao')
    }

    List<Requisicao> buscarNoPeriodoAtual(boolean isFinalizada, boolean isAprovada){
        LocalDate data = new LocalDate()
        return buscarPorMes(data.getMonthOfYear(), data.getYear(), isFinalizada, isAprovada)
    }

    List<Requisicao> buscarPorMes(int mes, int ano, boolean isFinalizada, boolean isAprovada){
        LocalDate abertura = ConfiguracaoService.getDiaAbertura(mes,ano)
        LocalDate fechamento = ConfiguracaoService.getDiaFechamento(mes,ano)
        return Requisicao.findAllByIsFinalizadaAndIsAprovadaAndDiaRequisitadoBetween(isFinalizada,isAprovada, abertura, fechamento)
    }

    Requisicao buscarRequisicao(LocalDate data, Funcionario funcionario){
        List<Requisicao> lista = Requisicao.findAllByDiaRequisitadoAndFuncionario(data, funcionario)
        if(lista.isEmpty()){
            return null
        }
        return lista.get(0)
    }

    boolean criarRequisicao(Requisicao requisicao){
        if(requisicao.save(flush: true)){
            registroPontoService.marcarRequisicaoNoDia(requisicao.diaRequisitado, requisicao.funcionario)
            return true
        }else{
            requisicao.errors.allErrors.each { println it }
        }
        return false
    }


    Requisicao get(int codigo) {
        Requisicao requisicao = Requisicao.findById(codigo)
        requisicao.horarios = requisicao.horarios.sort{it.horario}
        return requisicao
    }

    boolean finalizar(boolean reqAprovada, int id){
        Requisicao requisicao = get(id)
        requisicao.isAprovada = reqAprovada
        requisicao.isFinalizada = true
        if(reqAprovada){
            registroPontoService.removerTodosPontosDoDia(requisicao.diaRequisitado)
            for(horario in requisicao.horarios) {
                registroPontoService.registrar(requisicao.funcionario, requisicao.diaRequisitado, horario.horario)
            }
        }
        return requisicao.save() != null
    }
}
