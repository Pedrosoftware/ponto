package sistemaponto

class LoginController {

    static defaultAction = "auth"

    def autenticar() {
        println session
        if(params.login_error){
            render(view: 'autenticar', model: ['login_error':params.login_error])
        }
    }

    def negado(){
    }
}
