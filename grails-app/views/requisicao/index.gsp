<head>
    <meta name="layout" content="main">
</head>

<body>
    <div>
        <g:if test="${msg}">
            <h1>${msg}</h1>
        </g:if>
        <g:form controller="Requisicao" action="requisitar">
            <label>Dia: ${data}</label></br>
            <input type="hidden" name="diaRequisitado" value="${data}"/>
            <label>1º entrada</br><input type="text" name="horarios.entrada01"/></label></br>
            <label>1º saída</br><input type="text" name="horarios.saida01"/></label></br>
            <label>2º entrada</br><input type="text" name="horarios.entrada02"/></label></br>
            <label>2º saída</br><input type="text" name="horarios.saida02"/></label></br>
            <label for="justificativa">Justificativa</br><g:textArea name="justificativa"/></label></br>
            <input type="submit" value="solicitar"/>
        </g:form>
    </div>
</body>