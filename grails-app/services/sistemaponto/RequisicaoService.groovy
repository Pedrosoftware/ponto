package sistemaponto

import grails.gorm.DetachedCriteria
import grails.transaction.Transactional
import org.joda.time.LocalDate
import org.joda.time.LocalTime

@Transactional
class RequisicaoService {

    RegistroPontoService registroPontoService

    def listarEmAberto() {
        DetachedCriteria emAberto = Requisicao.where {
            isFinalizada == false
        }
        return emAberto.list(sort: 'dataSolicitacao')
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
            //TODO Devo ir no data na tabela de Registro de ponto alterar o ponto como solicitado
            registroPontoService.removerTodosPontosDoDia(requisicao.diaRequisitado)
            for(horario in requisicao.horarios) {
                registroPontoService.bater(requisicao.funcionario, requisicao.diaRequisitado, horario.horario)
            }
        }else{
            //TODO ação a ser executada quando uma requisição é reprovada
        }
        return requisicao.save() != null
    }
}
