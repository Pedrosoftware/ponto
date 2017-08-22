package sistemaponto

import service.ConfiguracaoService
import grails.transaction.Transactional
import org.joda.time.LocalDate

@Transactional
class FeriadoService {

    List<Feriado> listarDoMes() {
        return Feriado.findAllByDataGreaterThan(ConfiguracaoService.getUltimoDiaFechamento()).sort({it.data})
    }

    boolean inserir(Feriado feriado){
        return feriado.save() != null
    }

    boolean exists(LocalDate dataRecebida){
        return Feriado.findByData(dataRecebida)!= null
    }
}
