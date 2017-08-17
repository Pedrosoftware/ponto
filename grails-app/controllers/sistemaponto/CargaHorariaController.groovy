package sistemaponto

import grails.plugin.springsecurity.annotation.Secured
import org.springframework.dao.DataIntegrityViolationException

@Secured(['ROLE_ADMIN'])
class CargaHorariaController {

    CargaHorariaService cargaHorariaService

    static defaultAction = "home"

    def home() {
        render(view: 'home', model: ['cargas': cargaHorariaService.getAll()])
    }

    def novo(){
        render(view: 'salvar')
    }

    def editar() {
        render(view: 'salvar', model: ['carga': CargaHoraria.get(params.id)])
    }

    def excluir(CargaHoraria cargaHoraria) {
        Map model = [:]
        try{
            cargaHorariaService.excluir(cargaHoraria)
            model.msg = "Carga horária excluída com sucesso"
        }catch (DataIntegrityViolationException ex){
            model.msg = "Carga horária não pode ser excluída pois existem funcionários à utilizando"
        }

        model.cargas = cargaHorariaService.getAll()
        render(view: 'home', model: model)
    }

    def salvar(CargaHoraria cargaHoraria) {
        Map model = [:]
        if (cargaHorariaService.salvar(cargaHoraria)) {
            model['msg'] = "Carga horária salva com sucesso"
        } else {
            model['msg'] = "Falha ao salvar Carga horária"
        }
        model['cargas'] = cargaHorariaService.getAll()
        forward(controller: 'cargaHoraria', action: 'home', model: model)
    }
}
