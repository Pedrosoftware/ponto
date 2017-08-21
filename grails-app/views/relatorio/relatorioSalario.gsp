<html>
<head>
    <meta name="layout" content="admin">
    <asset:stylesheet src="seletorMes.css"/>
    <asset:stylesheet src="tabelaSalarios.css"/>
</head>

<body>
<div class="conteiner-base-branco">
    <g:render template="/home/seletorMes" model="[mesSelecionado:mesSelecionado, controller:'relatorio', action:'relatorioSalario']"/>
    <g:if test="${msg}">
        <h4>${msg}</h4>
    </g:if>
    <g:if test="${salarios}">
    <g:render template="tabelaSalarios" model="[salarios: salarios]"/>
    </g:if>
</div>
</body>
</html>