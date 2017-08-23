package sistemaponto

import grails.transaction.Transactional
import org.springframework.transaction.TransactionStatus

@Transactional
class CargaHorariaService {

    boolean salvar(CargaHoraria cargaHoraria) {
        if(cargaHoraria.save()){
            return true
        }
        return false
    }

    List<CargaHoraria> getAll(){
        return CargaHoraria.list(sort: 'id')
    }

    boolean excluir(CargaHoraria cargaHoraria){
        CargaHoraria.withTransaction{ TransactionStatus status ->
                cargaHoraria.delete()
                return true
        }
        return false
    }
}
