<html>
<head>
    <meta name="layout" content="main">
    <asset:stylesheet src="home.css"/>
    <asset:stylesheet src="tabelaPontos.css"/>
</head>

<body>
<div class="conteudo-superior">
    <g:render template="/home/resumoFuncionario" model="relatorio:relatorio"/>
    <g:render template="/home/baterPonto" model="msg:msg"/>
</div>

<div class="pontos-batidos conteiner-base-branco">
    <h4>Últimos pontos</h4>
    <g:render template="/home/tabelaPontos" model="[relatorio: relatorio, ajuste:true]"/>
</div>
</body>
</html>