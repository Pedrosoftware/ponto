<html>
<head>
    <meta name="layout" content="main">
</head>

<body>
    <h1>Nome: ${relatorio.funcionario.nome}</h1>
    <h2>Salário: R$ ${relatorio.funcionario.salario}</h2>
    <g:each in="${relatorio.dias}" var="dia">
        <h1>${dia.data}</h1>
        <g:each in="${dia.pontos}" var="ponto">
            ${ponto.hora.toString().substring(0,ponto.hora.toString().size()-4)}
            <g:if test="${ponto.isEntrada}">E</g:if>
            <g:else>S</g:else>
        </g:each>
            H.E.: ${dia.getHorasExtrasCumpridas().toString()}
            H.F.: ${dia.getHorasFaltantes().toString()}
    </g:each>
</body>
</html>