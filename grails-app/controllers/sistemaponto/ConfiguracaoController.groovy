package sistemaponto

import service.ConfiguracaoService
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN'])
class ConfiguracaoController {

    FeriadoService feriadoService
    static defaultAction = "home"

    def home() {
        Map model = chainModel? chainModel : [:]
        model.feriados = feriadoService.listarDoMes()
        model.diaFechamento = ConfiguracaoService.getConfig('diaFechamento')
        render(view: 'home', model: model)
    }

    def configurar() {
        Map model = [:]
        model.diaFechamento = ConfiguracaoService.getConfig('diaFechamento')
        if (!params.diaFechamento) {
            return encaminhar('Falha ao atualizar data de fechamento', model)
        }
        int dia = Integer.parseInt(params.diaFechamento as String)
        if (dia < 1 || dia > 31) {
            return encaminhar('Dia inválido', model)

        }
        ConfiguracaoService.escrever(ConfiguracaoService.DIA_FECHAMENTO, String.valueOf(dia))
        model.diaFechamento = dia
        return encaminhar('Dia de fechamento atualizado com sucesso', model)
    }

    private def encaminhar(String msg, Map model) {
        model.msg = msg
        return chain(controller: 'configuracao', model: model)
    }
}
