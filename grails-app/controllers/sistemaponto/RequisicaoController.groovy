package sistemaponto

import grails.plugin.springsecurity.annotation.Secured
import org.joda.time.LocalDate
import org.joda.time.LocalTime
import util.UtilitarioSpring

class RequisicaoController {

    static defaultAction = "index"
    RequisicaoService requisicaoService


    @Secured(['ROLE_ADMIN','ROLE_USER'])
    def index(){

    }

    @Secured(['ROLE_ADMIN','ROLE_USER'])
    def requisitar(){
        Requisicao requisicao = new Requisicao()
        requisicao.funcionario = UtilitarioSpring.getUsuarioLogado()
        requisicao.dataSolicitacao = new LocalDate()
        requisicao.diaRequisitado = LocalDate.fromDateFields(params.diaRequisitado as Date)
        requisicao.horarios = new ArrayList<>()
        requisicao.justificativa = params.justificativa
        params.horarios.each{
            requisicao.addToHorarios(createRequisicaoHorario("${it.value}"))
        }

        Map model = [:]
        if(requisicao.save()){
            model['msg'] = "Requisição enviada com sucesso, aguarde a avaliação do administrador"
        }else{
            model['msg'] = "Falha ao requisitar ponto passado. "
            requisicao.errors.allErrors.each { model['msg'] += it }
        }
        forward(controller: 'funcionario', action: 'home', model: model)
    }

    @Secured(['ROLE_ADMIN'])
    def listar(){
        render(view: 'lista', model: ['requisicoes':requisicaoService.listarEmAberto()])
    }

    @Secured(['ROLE_ADMIN'])
    def detalhe(){
        Map model = ['requisicao':requisicaoService.get(Integer.parseInt(params.id))]
        render(view: 'detalhe', model: model)
    }

    @Secured(['ROLE_ADMIN'])
    def aprovar(){
        boolean result = requisicaoService.finalizar(true, params.id as int)
        preparaMsgEEncaminhaPraView("aprova",result)
    }

    @Secured(['ROLE_ADMIN'])
    def recusar(){
        boolean cadastrou = requisicaoService.finalizar(false, params.id as int)
        preparaMsgEEncaminhaPraView("recusa",cadastrou)
    }

    private def preparaMsgEEncaminhaPraView(String aprovadoOuRecusado, boolean success){
        Map model = [:]
        if(success){
            model.msg = "Requisição ${aprovadoOuRecusado}da com sucesso"
        }else{
            model.msg = "Falha ao ${aprovadoOuRecusado}r requisição"
        }
        return forward(action: 'listar', model: model)
    }

    private static RequisicaoHorario createRequisicaoHorario(String horario){
        println "HORARIO:::${horario}"
        String[] strNumeros = horario.split(":")
        RequisicaoHorario rh = new RequisicaoHorario()
        rh.horario = new LocalTime(strNumeros[0] as int,strNumeros[1] as int, strNumeros[2] as int)
        return rh
    }
}
