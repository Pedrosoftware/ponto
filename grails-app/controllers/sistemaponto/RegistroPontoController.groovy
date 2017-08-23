package sistemaponto

import service.FormatadorDataHora
import grails.plugin.springsecurity.annotation.Secured
import org.joda.time.LocalDate
import org.joda.time.LocalTime

@Secured(['ROLE_USER', 'ROLE_ADMIN'])
class RegistroPontoController {


    RegistroPontoService registroPontoService

    @Secured(['ROLE_USER'])
    def baterPonto() {
        Map model = [:]
        if (registroPontoService.registrar()) {
            model['msg'] = "Ponto registrado"
            return redirect(controller: 'funcionario', action: 'homepadrao', params: model)
        }
        model['msg'] = "Falha ao registrar o ponto"
        redirect(controller: 'funcionario', action: 'homepadrao', params: model)
    }

    @Secured(['permitAll'])
    def baterPontoMaquina(int id) {
        String mensagem = ""
        if(!id){
            mensagem = "Parâmetro id não informado"
            return render(contentType: "application/json") {
                resultado(msg: mensagem)
            }
        }
        Funcionario funcionario = Funcionario.get(id)
        if (funcionario) {
            LocalDate data = new LocalDate()
            LocalTime hora = new LocalTime()
            if (registroPontoService.registrar(funcionario, data, hora)) {
                mensagem = "Ponto registrado para o funcionário ${funcionario.nome}"
            } else {
                mensagem = "Falha ao registrar ponto para o funcionário ${funcionario.nome}"
            }
            return render(contentType: "application/json") {
                resultado(msg: mensagem,
                horario: "${FormatadorDataHora.toDateTime(data, hora)}")
            }
        }
        return render(status: 404, contentType: "application/json") {
            resultado(msg: "Funcionário não encontrado")
        }
    }
}
