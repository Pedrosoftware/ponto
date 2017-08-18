<!DOCTYPE html>
<head>
    <title>Sistema de Ponto</title>
    <asset:stylesheet src="bootstrap.css"/>
    <asset:stylesheet src="bootstrap-datetimepicker.css"/>
    <asset:stylesheet src="main.css"/>
    <asset:stylesheet src="login.css"/>
    <asset:javascript src="jquery.js"/>
    <g:layoutHead/>
</head>

<body>

<div class="base">
    <div class="cabecalho container-fluid">
        <nav class="navbar navbar-expand-lg navbar-light  bg-light">
            <asset:image src="logozg.png"/>
            <a class="navbar-brand" href="#">Sistema de Ponto</a>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="#">Resumo</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Registros</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Pontos</a>
                    </li>
                </ul>
            </div>
            <g:if test="${session.SPRING_SECURITY_CONTEXT}">
                <a href="/SistemaPonto/logout">sair</a>
            </g:if>
        </nav>
    </div>

    <div class="corpo container-fluid">
        <g:layoutBody/>
    </div>

    <div class="rodape container-fluid">
        <asset:image src="logozg.png"/>
    </div>

</div>
</body>
</html>