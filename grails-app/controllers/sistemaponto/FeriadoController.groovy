package sistemaponto

import entity.ConfiguracaoService
import grails.plugin.springsecurity.annotation.Secured
import org.joda.time.LocalDate

@Secured(['ROLE_ADMIN'])
class FeriadoController {

    static defaultAction = "home"
    FeriadoService feriadoService

    def home() {
        Map model = [:]
        model.feriados = feriadoService.listarDoMes()
        return render(view: "home", model: model)
    }

    def cadastrar() {
        Map model = [:]
        LocalDate dataRecebida = LocalDate.fromDateFields(params.dia as Date)
        if (!dataRecebida) {
            return encaminhar("Falha. Data não informada", model)
        }
        model.diaInformado = dataRecebida.toDate()
        LocalDate dataFechamento = ConfiguracaoService.getUltimoDiaFechamento()
        if (dataRecebida <= dataFechamento) {
            return encaminhar("Falha, data anterior ao data de início do período atual", model)
        }

        dataFechamento = dataFechamento.plusMonths(1).minusDays(1)
        if(dataRecebida > dataFechamento){
            return encaminhar("Falha, data posterior ao fim do período atual", model)
        }

        if (feriadoService.exists(dataRecebida)) {
            return encaminhar("Já existe um feriado nessa data", model)
        }

        Feriado feriado = new Feriado()
        feriado.data = dataRecebida

        if (feriadoService.inserir(feriado)) {
            return encaminhar("Feriado inserido com sucesso", model)
        }
        return encaminhar("Falha ao inserir feriado", model)
    }

    private def encaminhar(String msg, Map model) {
        model.msg = msg
        return chain(controller: 'configuracao', model: model)
    }
}
