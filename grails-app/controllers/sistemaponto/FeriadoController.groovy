package sistemaponto

import entity.ConfiguracaoService
import grails.plugin.springsecurity.annotation.Secured
import org.joda.time.LocalDate

@Secured(['ROLE_ADMIN'])
class FeriadoController {

    static defaultAction = "home"
    FeriadoService feriadoService

    def home(){
        Map model = [:]
        model.feriados = feriadoService.listarDoMes()
        return render(view: "home", model: model)
    }

    def cadastrar(){
        Map model = [:]
        LocalDate dataRecebida = LocalDate.fromDateFields(params.dia as Date)
        if(!dataRecebida){
            model.msg = "Falha. Data não informada"
            return forward (controller: 'feriado', action: 'home', model: model)
        }

        if(dataRecebida <= ConfiguracaoService.getDiaFechamento()){
            model.msg = "Falha ao cadastrar feriado. Data anterior ao data de fechamento"
            return forward (controller: 'feriado', action: 'home', model: model)
        }

        if(feriadoService.exists(dataRecebida)){
            model.msg = "Falha ao cadastrar feriado. Data já cadastrada"
            return forward (controller: 'feriado', action: 'home', model: model)
        }

        Feriado feriado = new Feriado()
        feriado.data = dataRecebida

        if(feriadoService.inserir(feriado)){
            model.msg = "Feriado inserido com sucesso"
        }else{
            model.msg = "Falha ao inserir feriado"
        }
        return forward (controller: 'feriado', action: 'home', model: model)
    }
}
