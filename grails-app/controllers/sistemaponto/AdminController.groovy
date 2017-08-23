package sistemaponto

import util.FormatadorDataHora
import entity.Historico
import grails.plugin.springsecurity.annotation.Secured

/**
 * Created by pedro on 01/08/17.
 */
@Secured(['ROLE_ADMIN'])
class AdminController {

    FeriadoService feriadoService
    RequisicaoService requisicaoService
    RelatorioService relatorioService
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

    private Map preencherBlocoFuncionario() {
        Map model = [:]
        model['Total funcionários'] = funcionarioService.listar().size()
        model['Soma dos salários'] = funcionarioService.salarioTotal()
        model['Entraram este mês'] = funcionarioService.entraramNoMes().size()
        model['Trabalhando agora'] = funcionarioService.trabalhandoNoMomento().size()
        return model
    }

    private Map preencherBlocoHoras() {
        Map model = [:]
        List<Historico> historicos = relatorioService.criarParaTodosOsFuncionarios()
        model['Total'] = FormatadorDataHora.toTime(relatorioService.totalHorasExtras(historicos))
        model['Horas 50%'] = FormatadorDataHora.toTime(relatorioService.totalHorasExtras50(historicos))
        model['Horas 100%'] = FormatadorDataHora.toTime(relatorioService.totalHorasExtras100(historicos))
        return model
    }

    private Map preencherBlocoFeriados() {
        List<Feriado> feriados = feriadoService.listarDoMes()
        Map model = [:]
        model['Total'] = feriados.size()
        for(int i = 0; i < feriados.size(); i++){
            model["${i+1}º"] = FormatadorDataHora.toDate(feriados.get(i).data)
        }
        return model
    }

    private Map preencherBlocoRequisicoes() {
        boolean FINALIZADA = true
        boolean APROVADA = true
        Map model = [:]
        model['Em aberto'] = requisicaoService.buscarNoPeriodoAtual(!FINALIZADA, !APROVADA).size()
        model['Aprovadas'] = requisicaoService.buscarNoPeriodoAtual(FINALIZADA, APROVADA).size()
        model['Recusadas'] = requisicaoService.buscarNoPeriodoAtual(FINALIZADA, !APROVADA).size()
        return model
    }
}
