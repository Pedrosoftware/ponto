class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/pontos"(controller: "registroPonto", action:[POST:"baterPontoMaquina"])
        "/"(controller: 'login', action: 'autenticar')
        "500"(view:'/error')
        "404"(view:'/notfound')
	}
}
