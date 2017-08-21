<!DOCTYPE html>
<head>
    <title>Sistema de Ponto</title>
    <asset:stylesheet src="bootstrap.css"/>
    <asset:stylesheet src="bootstrap-datetimepicker.css"/>
    <asset:stylesheet src="main.css"/>
    <asset:stylesheet src="home.css"/>
    <asset:javascript src="jquery.js"/>
    <g:layoutHead/>
</head>

<body>

<div class="base">
    <div class="cabecalho container-fluid">
        <nav class="navbar navbar-expand-lg navbar-light  bg-light">
            <asset:image id="banner-superior" src="logozg.png"/>
            <div class="navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <g:link controller="requisicao" action="listar" class="nav-link">Requisições</g:link>
                    </li>
                    <li class="nav-item active">
                        <g:link controller="funcionario" action="listar" class="nav-link">Funcionários</g:link>
                    </li>
                    <li class="nav-item active">
                        <g:link controller="cargaHoraria" class="nav-link">Cargas</g:link>
                    </li>
                    <li class="nav-item active">
                        <g:link controller="funcionario" class="nav-link">Rel pontos</g:link>
                    </li>
                    <li class="nav-item active">
                        <g:link controller="funcionario" class="nav-link">Rel salários</g:link>
                    </li>
                    <li class="nav-item">
                        <g:link controller="funcionario" class="nav-link">Configurações</g:link>
                    </li>
                </ul>
            </div>
            <g:if test="${session.SPRING_SECURITY_CONTEXT}">
                <a id="botao-sair" href="/SistemaPonto/logout">sair</a>
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