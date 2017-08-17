<html>
<head>
    <meta name="layout" content="main">
</head>

<body>
<p>Nome: ${requisicao.funcionario.nome}</p></br>
<p>Data: ${requisicao.dataSolicitacao}</p></br>
<p>Dia solicitado: ${requisicao.diaRequisitado}</p></br>
<g:each in="${requisicao.horarios}" var="ponto">
    <p>${ponto.horario.toString().substring(0,ponto.horario.toString().size()-4)}</p></br>
</g:each>

<p>Justificativa: ${requisicao.justificativa}</p></br>
<g:form controller="requisicao" action="aprovar">
    <input type="hidden" value="${requisicao.id}" name="id"/>
    <input type="submit" value="aprovar"/>
</g:form>
<g:form controller="requisicao" action="recusar">
    <input type="hidden" value="${requisicao.id}" name="id"/>
    <input type="submit" value="recusar"/>
</g:form>
</body>
</html>