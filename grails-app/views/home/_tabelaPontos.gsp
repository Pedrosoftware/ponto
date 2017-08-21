<table class="tabela-ultimos-pontos">
    <thead>
    <tr>
        <th rowspan="2" class="dado-tabela">Data</th>
        <th colspan="2" class="dado-tabela">Horas</th>
        <th colspan="2" class="dado-tabela">Horas Extras</th>
        <g:if test="${ajuste}"><th rowspan="2" class="dado-tabela">Ajuste</th></g:if>
        <th rowspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Pontos</th>
    </tr>
    <tr>
        <th class="dado-tabela">Trabalhadas</th>
        <th class="dado-tabela">Faltantes</th>
        <th class="dado-tabela">50%</th>
        <th class="dado-tabela">100%</th>
    </tr>
    </thead>
    <tbody>
    <g:each in="${relatorio.dias}" var="dia">
        <tr>
            <td class="dado-tabela"><ponto:conversor localDate="${dia.data}"/></td>
            <td class="dado-tabela"><ponto:conversor duration="${dia.cargaCumprida}"/></td>
            <td class="dado-tabela"><ponto:conversor duration="${dia.getHorasFaltantes()}"/></td>
            <td class="dado-tabela">
                <g:if test="${dia.tipoHora == entity.TipoHoraExtra.HORA50}">
                    <ponto:conversor duration="${dia.getHorasExtrasCumpridas()}"/>
                </g:if>
                <g:else><ponto:conversor duration="${new org.joda.time.Duration(0)}"/></g:else>
            </td>
            <td class="dado-tabela">
                <g:if test="${dia.tipoHora == entity.TipoHoraExtra.HORA100}">
                    <ponto:conversor duration="${dia.getHorasExtrasCumpridas()}"/>
                </g:if>
                <g:else><ponto:conversor duration="${new org.joda.time.Duration(0)}"/></g:else>
            </td>
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