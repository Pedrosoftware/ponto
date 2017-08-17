<%@ page import="org.joda.time.Duration" %>
<html>
<head>
    <meta name="layout" content="main">
</head>

<body>
<g:form controller="relatorio" action="relatorioPonto">
    <g:datePicker name="data" precision="month" value="${mesSelecionado}"/>
    <g:select name="idFuncionario"
              from="${funcionarios}"
              optionKey="id"
              optionValue="nome"
              value="${funcionario?.id}"/>
    <input type="submit" value="gerar"/>
</g:form>
<g:if test="${relatorio}">
    <h1>Nome: ${relatorio.funcionario.nome}</h1>
    <table>
        <thead>
        <tr>
            <td>Data</td>
            <td>Carga Cumprida</td>
            <td>Hora Faltante</td>
            <td>H.E. 50%</td>
            <td>H.E. 100%</td>
            <td>1º Entrada</td>
            <td>1º Saída</td>
            <td>2º Entrada</td>
            <td>2º Saída</td>
        </tr>
        </thead>
        <tfoot>
        <tr>
            <td>Total:</td>
            <td><ponto:conversor duration="${relatorio.horasTrabalhadas}"/></td>

            <td><ponto:conversor duration="${relatorio.horasFaltantes}"/></td>
            <td><ponto:conversor duration="${relatorio.horasExtras50}"/></td>
            <td><ponto:conversor duration="${relatorio.horasExtras100}"/> </td>
        </tr>
        </tfoot>
        <tbody>
        <g:each in="${relatorio.dias}" var="dia">
            <tr>
                <td><ponto:conversor localDate="${dia.data}"/> </td>
                <td><ponto:conversor duration="${dia.cargaCumprida}"/></td>
                <td><ponto:conversor duration="${dia.getHorasFaltantes()}"/></td>
                <td>
                    <g:if test="${dia.tipoHora == entity.TipoHoraExtra.HORA50}">
                        <ponto:conversor duration="${dia.getHorasExtrasCumpridas()}"/>
                    </g:if>
                    <g:else><ponto:conversor duration="${new org.joda.time.Duration(0)}"/></g:else>
                </td>
                <td>
                <g:if test="${dia.tipoHora == entity.TipoHoraExtra.HORA100}">
                    <ponto:conversor duration="${dia.getHorasExtrasCumpridas()}"/>
                </g:if>
                <g:else><ponto:conversor duration="${new org.joda.time.Duration(0)}"/></g:else>
            </td>
                <g:each in="${dia.pontos}" var="pontos">
                    <td><ponto:conversor localTime="${pontos.hora}"/></td>
                </g:each>
            </tr>
        </g:each>
        </tbody>
    </table>
</g:if>
</body>
</html>