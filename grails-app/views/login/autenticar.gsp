<html>
<head>
    <asset:stylesheet src="bootstrap.css"/>
    <asset:stylesheet src="main.css"/>
    <asset:stylesheet src="login.css"/>
    <asset:javascript src="jquery.js"/>
</head>
<body>
<div class="base-container container-fluid">
    <div class="formulario-container conteiner-base-branco">
        <g:if test="${login_error}">
            <h4>usuário ou senha incorretos</h4>
        </g:if>
        <h1>Sistema de Ponto</h1>
        <form action='/SistemaPonto/sslogin' method='POST' id='loginForm' class='cssform' autocomplete='off'>
                <input class="form-control formulario-item" type="text" id="usuario" name="username" placeholder="USUÁRIO" value="pedro"/>
                <input class="form-control formulario-item" type="password" id="senha" name="password" PLACEHOLDER="SENHA" value="fibo@123"/>
                <input class="btn btn-dark form-control formulario-item" type="submit" value="entrar"/>
        </form>
    </div>
</div>
</body>
</html>