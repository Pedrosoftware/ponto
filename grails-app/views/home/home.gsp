<html>
<head>
    <meta name="layout" content="main">
</head>

<body>
<div class="conteudo-superior">
    <div class="resumo-funcionario">
        <h4>Informações</h4>

        <p><span>Nome:</span> ${relatorio.funcionario.nome}</p>

        <p><span>Salário:</span> R$ ${relatorio.funcionario.salario}</p>

        <p><span>Admissão:</span> <ponto:conversor localDate="${relatorio.funcionario.dataAdmissao}"/></p>

        <p><span>Horas extras:</span> <ponto:conversor
                duration="${relatorio.horasExtras50 + relatorio.horasExtras100}"/></p>

        <p><span>Horas faltantes:</span> <ponto:conversor duration="${relatorio.horasFaltantes}"/></p>
    </div>

    <div class="bater-ponto">
        <h4>Horário</h4>

        <h1 class="relogio">00:00:00</h1>
        <g:form controller="funcionario" action="baterPonto">
            <input class="btn btn-block btn-dark" type="submit" value="bater ponto"/>
        </g:form>
        <g:if test="${msg}">
            <h4>${msg}</h4>
        </g:if>
    </div>
</div>

<div class="pontos-batidos">
    <h4>Últimos pontos</h4>
    <table class="tabela-ultimos-pontos">
        <thead>
        <tr>
            <td class="dado-tabela">Data</td>
            <td class="dado-tabela">H. Extras</td>
            <td class="dado-tabela">H. Faltantes</td>
            <td class="dado-tabela">Ajuste</td>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Pontos</td>
        </tr>
        </thead>
        <tbody>
        <g:each in="${relatorio.dias}" var="dia">
            <tr>
                <td class="dado-tabela"><ponto:conversor localDate="${dia.data}"/></td>
                <td class="dado-tabela"><ponto:conversor duration="${dia.getHorasExtrasCumpridas()}"/></td>
                <td class="dado-tabela"><ponto:conversor duration="${dia.getHorasFaltantes()}"/></td>
                <td class="dado-tabela"><ponto:solicitacaoAjuste dia="${dia}" funcionario="${relatorio.funcionario}"/></td>
                <td><g:each in="${dia.pontos}" var="ponto">
                    <ponto:conversor localTime="${ponto.hora}"/><g:if test="${ponto.isEntrada}"><span
                            class="ponto-entrada">E&nbsp;</span></g:if><g:else><span
                            class="ponto-saida">S&nbsp;</span></g:else>
                </g:each></td>
            </tr>
        </g:each>
        </tbody>
    </table>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        var currentdate = new Date();

        function atualizaRelogio() {
            currentdate = new Date();
            var hora = currentdate.getHours() < 10 ? "0" + currentdate.getHours() : currentdate.getHours();
            var minuto = currentdate.getMinutes() < 10 ? "0" + currentdate.getMinutes() : currentdate.getMinutes();
            var segundo = currentdate.getSeconds() < 10 ? "0" + currentdate.getSeconds() : currentdate.getSeconds();
            $('.relogio').html(hora + ":" + minuto + ":" + segundo);
        }
        atualizaRelogio();
        window.setInterval(atualizaRelogio, 1000);

    });
</script>
</body>
</html>