<%@ page import="org.joda.time.Duration" %>
<html>
<head>
    <meta name="layout" content="admin">
    <asset:stylesheet src="tabelaPontos.css"/>
    <asset:stylesheet src="home.css"/>
    <asset:stylesheet src="seletorMes.css"/>
</head>

<body>
<div class="pontos-batidos conteiner-base-branco">
    <g:render template="/home/seletorMes" model="[mesSelecionado:mesSelecionado, controller:'relatorio', action:'relatorioPonto',funcionarios:funcionarios]"/>

    <g:if test="${relatorio}">
        <g:render template="/home/tabelaPontos" model="[relatorio: relatorio]"/>
    </g:if>
</div>
</body>
</html>
