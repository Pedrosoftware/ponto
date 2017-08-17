<html>
<head>
    <meta name="layout" content="main">
</head>

<body>
    <g:if test="${msg}">
        <h1>${msg}</h1>
    </g:if>
    <div>
        <h1>Feriados do período</h1>
        <g:each in="${feriados}" var="feriado">
            <p>${feriado.data}</p>
        </g:each>

    </div>
    </br>
    <div>
        <g:form controller="feriado" action="cadastrar">
            <label>Dia</br><g:datePicker name="dia" precision="day"/></label></br>
            <input type="submit" value="cadastrar"/>
        </g:form>
    </div>
</body>
</html>