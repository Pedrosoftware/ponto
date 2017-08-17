<html>
<head>
    <meta name="layout" content="main"/>
</head>

<body>
<g:if test="${login_error}">
    <h1>falha ao efetuar o login</h1>
</g:if>
<h1>Login</h1>
    <form action='/SistemaPonto/sslogin' method='POST' id='loginForm' class='cssform' autocomplete='off'>
            <label for="usuario">
                <input type="text" id="usuario" name="username" value="pedro"/></br>
                <input type="password" id="senha" name="password" value="fibo@123"/></br>
                <p id="remember_me_holder"></br>
                    <input type='checkbox' class='chk' name='_spring_security_remember_me' id='remember_me' />
                <label for='remember_me'>Lembrar</label>
                </p></br>
                <input type="submit"/>
            </label>
    </form>
</body>
</html>