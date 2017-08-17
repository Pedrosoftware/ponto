class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/pontos/$id"(controller: "registroPonto", action: "baterPontoMaquina")
        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notfound')
	}
}
