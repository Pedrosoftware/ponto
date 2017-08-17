<html>
<head>
    <meta name="layout" content="main">
</head>

<body>
    <div>
        <ponto:menu/>
        <g:if test="${msg}">
            <h1>${msg}</h1>
        </g:if>
        <g:form controller="RegistroPonto" action="baterPonto">
            <input type="submit" value="bater ponto"/>
        </g:form>
    </div>
</body>
</html>