<html>
<head>
    <meta name="layout" content="main">
    <title>Carga Horária</title>
</head>

<body>
    <g:if test="${msg}">
        <h1>${msg}</h1>
    </g:if>
    <h2>Carga horária diária</h2>
    <g:link controller="cargaHoraria" action="novo">novo</g:link>
    <table>
        <tr>
            <td>Código</td>
            <td>Descrição</td>
            <td>Seg</td>
            <td>Ter</td>
            <td>Qua</td>
            <td>Qui</td>
            <td>Sex</td>
            <td>Sab</td>
            <td>Dom</td>
            <td>ação</td>
            <td>ação</td>
        </tr>
        <g:each var="carga" in="${cargas}">
        <tr>
            <td>${carga.id}</td>
            <td>${carga.descricao}</td>
            <td>${carga.segunda}</td>
            <td>${carga.terca}</td>
            <td>${carga.quarta}</td>
            <td>${carga.quinta}</td>
            <td>${carga.sexta}</td>
            <td>${carga.sabado}</td>
            <td>${carga.domingo}</td>
            <td><g:link controller="cargaHoraria" action="editar" id="${carga.id}">Editar</g:link></td>
            <td><g:link controller="cargaHoraria" action="excluir" id="${carga.id}">Excluir</g:link></td>
        </tr>
        </g:each>
    </table>
</body>
</html>