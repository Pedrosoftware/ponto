<html>
<head>
    <meta name="layout" content="admin">
    <asset:stylesheet src="cargaLista.css"/>
</head>

<body>
    <div class="conteiner-base-branco">
        <div id="conteiner-carga-interno">
            <g:if test="${msg}">
                <h4>${msg}</h4>
            </g:if>
            <h2>Carga horária</h2>
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
                </tr>
                <g:each var="carga" in="${cargas}">
                    <tr>
                        <td><g:link class="dado-tab-carga" controller="cargaHoraria" action="editar" id="${carga.id}">${carga.id}</g:link></td>
                        <td><g:link class="dado-tab-carga" controller="cargaHoraria" action="editar" id="${carga.id}">${carga.descricao}</g:link></td>
                        <td><g:link class="dado-tab-carga" controller="cargaHoraria" action="editar" id="${carga.id}">${carga.segunda}</g:link></td>
                        <td><g:link class="dado-tab-carga" controller="cargaHoraria" action="editar" id="${carga.id}">${carga.terca}</g:link></td>
                        <td><g:link class="dado-tab-carga" controller="cargaHoraria" action="editar" id="${carga.id}">${carga.quarta}</g:link></td>
                        <td><g:link class="dado-tab-carga" controller="cargaHoraria" action="editar" id="${carga.id}">${carga.quinta}</g:link></td>
                        <td><g:link class="dado-tab-carga" controller="cargaHoraria" action="editar" id="${carga.id}">${carga.sexta}</g:link></td>
                        <td><g:link class="dado-tab-carga" controller="cargaHoraria" action="editar" id="${carga.id}">${carga.sabado}</g:link></td>
                        <td><g:link class="dado-tab-carga" controller="cargaHoraria" action="editar" id="${carga.id}">${carga.domingo}</g:link></td>
                    </tr>
                </g:each>
            </table>
            <g:link class="btn" controller="cargaHoraria" action="nova">novo</g:link>
        </div>
    </div>
</body>
</html>