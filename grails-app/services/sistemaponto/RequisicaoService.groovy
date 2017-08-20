package sistemaponto

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


    def buscarRequisicao(LocalDate data, Funcionario funcionario){
        List<Requisicao> lista = Requisicao.findAllByDiaRequisitadoAndFuncionario(data, funcionario)
        if(lista.isEmpty()){
            return null
        }
        return lista.get(0)
    }

    def criarRequisicao(Requisicao requisicao){
        if(requisicao.save()){
            registroPontoService.marcarRequisicaoNoDia(requisicao.diaRequisitado, requisicao.funcionario)
            return true
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
