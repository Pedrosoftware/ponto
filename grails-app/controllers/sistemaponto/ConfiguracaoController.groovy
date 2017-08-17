package sistemaponto

import entity.ConfiguracaoService
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN'])
class ConfiguracaoController {

    static defaultAction = "home"

    def home() {
        return ['diaFechamento': ConfiguracaoService.getConfig('diaFechamento')]
    }

    def configurar() {
        Map mapa = [:]
        println params
        if (params.diaFechamento) {
            int dia = Integer.parseInt(params.diaFechamento as String)
            if (dia > 0 && dia <= 31) {
                ConfiguracaoService.escrever(ConfiguracaoService.DIA_FECHAMENTO, String.valueOf(dia))
                mapa = ['msg': 'Dia de fechamento atualizado com sucesso']

            } else {
                mapa = ['msg': 'Formato de data inválido, informe um número entre 1 e 31']
            }
        } else {
            mapa = ['msg': 'Falha ao atualizar data de fechamento']
        }
        mapa['diaFechamento'] = ConfiguracaoService.getConfig('diaFechamento')
        return render(view: 'home', model: mapa)
    }
}
