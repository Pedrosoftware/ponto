package sistemaponto

import grails.gorm.DetachedCriteria
import grails.transaction.Transactional
import org.joda.time.LocalDate

@Transactional
class RequisicaoService {

    RegistroPontoService registroPontoService

    def listarEmAberto() {
        DetachedCriteria emAberto = Requisicao.where {
            isFinalizada == false
        }
        return emAberto.list(sort: 'dataSolicitacao')
    }

    List<Requisicao> buscarRequisicoes(LocalDate dataInicio, LocalDate dataFim){
        return Requisicao.findAllByDiaRequisitadoBetween(dataInicio,dataFim)
    }
    List<Requisicao> buscarRequisicoesMesCorrente(){

    }


    def get(int codigo) {
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
