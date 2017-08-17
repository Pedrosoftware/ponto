<html>
<head>
    <meta name="layout" content="main">
</head>

<body>
<table>

    <g:if test="msg">
        <h1>${msg}</h1>
    </g:if>
    <tr>
        <td>data Solicitação</td>
        <td>Dia Requisitado</td>
        <td>Funcionario</td>
    </tr>
    <g:each var="requisicao" in="${requisicoes}">
        <tr>
            <td>${requisicao.dataSolicitacao}</td>
            <td>${requisicao.diaRequisitado}</td>
            <td>${requisicao.funcionario.nome}</td>
            <td><g:link controller="requisicao" action="detalhe" id="${requisicao.id}">ver</g:link></td>
        </tr>
    </g:each>
</table>
</body>
</html>