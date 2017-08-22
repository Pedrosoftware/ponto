package sistemaponto

import entity.ConfiguracaoService
import entity.FormatadorDataHora
import grails.plugin.springsecurity.annotation.Secured
import org.joda.time.Duration

/**
 * Created by pedro on 01/08/17.
 */
@Secured(['ROLE_ADMIN'])
class AdminController {

    FeriadoService feriadoService
    RequisicaoService requisicaoService
    FuncionarioService funcionarioService
    static defaultAction = "home"

    def home() {

        Map blocoFuncionario = preencherBlocoFuncionario()
        Map blocoHoras = preencherBlocoHoras()
        Map blocoFeriados = preencherBlocoFeriados()
        Map blocoRequisicoes = preencherBlocoRequisicoes()
        Map model = [:]
        model.blocoPai = ['Funcionários':blocoFuncionario,
                          'Horas':blocoHoras,
                          'Feriados':blocoFeriados,
                          'Requisições':blocoRequisicoes]

        return render(view: '/home/homeAdmin', model: model)
    }

    Map preencherBlocoFuncionario() {
        Map model = [:]
        model['Total funcionários'] = funcionarioService.listar().size()
        model['Soma dos salários'] = funcionarioService.salarioTotal()
        model['Entraram este mês'] = funcionarioService.entraramNoMes().size()
        model['Trabalhando agora'] = 23
        return model
    }

    Map preencherBlocoHoras() {
        Map model = [:]
        model['total'] = FormatadorDataHora.toTime(new Duration(32302323))
        model['horas 50%'] = FormatadorDataHora.toTime(new Duration(1230919))
        model['horas 100%'] = FormatadorDataHora.toTime(new Duration(12302323))
        return model
    }

    Map preencherBlocoFeriados() {
        List<Feriado> feriados = feriadoService.listarDoMes()
        Map model = [:]
        model['Total'] = feriados.size()
        for(int i = 0; i < feriados.size(); i++){
            model["${i+1}º"] = FormatadorDataHora.toDate(feriados.get(i).data)
        }
        return model
    }

    Map preencherBlocoRequisicoes() {
        boolean FINALIZADA = true
        boolean APROVADA = true
        Map model = [:]
        model['em aberto'] = requisicaoService.buscarNoPeriodoAtual(!FINALIZADA, !APROVADA).size()
        model['aprovadas'] = requisicaoService.buscarNoPeriodoAtual(FINALIZADA, APROVADA).size()
        model['recusadas'] = requisicaoService.buscarNoPeriodoAtual(FINALIZADA, !APROVADA).size()
        return model
    }
}
