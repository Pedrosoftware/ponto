<html>
<head>
    <meta name="layout" content="main">
</head>
<body>
    <table>
        <tr>
            <td>Nome</td>
        </tr>
        <g:each in="${funcionarios}" var="funcionario">
            <tr>
            <td><g:link controller="relatorio" action="relatorio" id="${funcionario.id}">${funcionario.nome}</g:link></td>
            </tr>
        </g:each>
    </table>
</body>
</html>