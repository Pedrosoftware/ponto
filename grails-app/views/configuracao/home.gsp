<head>
    <meta name="layout" content="admin">
</head>

<body>
<div>
    <g:if test="${msg}">
        <h1>${msg}</h1>
    </g:if>
    <g:form controller="configuracao" action="configurar">
        <label for="dia">Informe o dia do fechamento do mês</br>
            <input type="text" name="diaFechamento" id="dia" value="${diaFechamento}"/>
        </label></br>
        <h1> cadastrar feriados aqui tbm</h1>
        <input type="submit" value="enviar"/>
    </g:form>
</div>
</body>