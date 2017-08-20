<table class="tabela-ultimos-pontos">
    <thead>
    <tr>
        <td class="dado-tabela">Data</td>
        <td class="dado-tabela">Trabalhadas</td>
        <td class="dado-tabela">Extras</td>
        <td class="dado-tabela">Faltantes</td>
        <g:if test="${ajuste}"><td class="dado-tabela">Ajuste</td></g:if>

        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Pontos</td>
    </tr>
    </thead>
    <tbody>
    <g:each in="${relatorio.dias}" var="dia">
        <tr>
            <td class="dado-tabela"><ponto:conversor localDate="${dia.data}"/></td>
            <td class="dado-tabela"><ponto:conversor duration="${dia.cargaCumprida}"/></td>
            <td class="dado-tabela"><ponto:conversor duration="${dia.getHorasExtrasCumpridas()}"/></td>
            <td class="dado-tabela"><ponto:conversor duration="${dia.getHorasFaltantes()}"/></td>
            <g:if test="${ajuste}">
                <td class="dado-tabela"><ponto:solicitacaoAjuste dia="${dia}" funcionario="${relatorio.funcionario}"/></td>
            </g:if>
            <td><g:each in="${dia.pontos}" var="ponto">
                <ponto:conversor localTime="${ponto.hora}"/><g:if test="${ponto.isEntrada}"><span
                        class="ponto-entrada">E&nbsp;</span></g:if><g:else><span
                        class="ponto-saida">S&nbsp;</span></g:else>
            </g:each></td>
        </tr>
    </g:each>
    </tbody>
</table>