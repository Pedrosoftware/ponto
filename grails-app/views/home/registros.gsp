<!DOCTYPE html>
<head>
    <meta content="main" name="layout">
    <asset:stylesheet src="tabelaPontos.css"/>
    <asset:stylesheet src="registros.css"/>
    <asset:stylesheet src="seletorMes.css"/>

</head>
<body>
<div class="pontos-batidos conteiner-base-branco">
    <g:render template="/home/seletorMes" model="[mesSelecionado: mesSelecionado, controller:'relatorio', action:'relatorio']"/>
    <g:if test="${relatorio}">
        <h4 class="registro-h4">Pontos registrados no mês <ponto:conversor date="${mesSelecionado}"/></h4>
        <g:render template="/home/tabelaPontos" model="[relatorio: relatorio]"/>
    </g:if>
    <g:else>
        <g:if test="${msg}">
            <h4 class="registro-h4 registro-mensagem">${msg}</h4>
        </g:if>
    </g:else>
</div>
</body>