<html>
<head>
    <meta name="layout" content="admin">
</head>

<body>
<g:form controller="relatorio" action="relatorioSalario">
    <g:datePicker name="data" precision="month" value="${mesSelecionado}"/>
    <input type="submit" value="gerar"/>
</g:form>
<g:if test="${salarios}">
    <table>
        <thead>
        <tr>
            <td>Nome</td>
            <td>Salário base</td>
            <td>H.E. 50%</td>
            <td>H.E. 100%</td>
            <td>H.F.</td>
            <td>Salário recebido</td>
        </tr>
        </thead>
        <tfoot>
        <tr>
            <td>Total:</td>
        </tr>
        </tfoot>
        <tbody>
            <g:each in="${salarios}" var="${salario}">
                <tr>
                    <td>${salario.funcionario.nome}</td>
                    <td><ponto:conversor valor="${salario.funcionario.salario}"/> </td>
                    <td><ponto:conversor valor="${salario.valorHora50}"/></td>
                    <td><ponto:conversor valor="${salario.valorHora100}"/></td>
                    <td><ponto:conversor valor="${salario.valorDescontado*-1}"/></td>
                    <td><ponto:conversor valor="${salario.funcionario.salario+salario.valorHora50+salario.valorHora100-salario.valorDescontado}"/></td>
                </tr>
            </g:each>
        </tbody>
    </table>
</g:if>
</body>
</html>