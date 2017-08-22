<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="admin">
    <asset:stylesheet src="homeadmin.css"/>
</head>

<body>
<div class="conteiner-homeadmin">
    <g:if test="${blocoPai}">
        <g:each in="${blocoPai}" var="bloco">
            <div class="homeadmin-bloco conteiner-base-branco">
                <table id="tabela-homeadmin">
                    <thead>
                    <tr>
                        <th colspan="2"><h4>${bloco.key}</h4></th>
                    </tr>
                    </thead>
                    <tbody>
                    <g:each in="${bloco.value}" var="item">
                        <tr>
                            <td class="td-admin-esquerda">${item.key}</td>
                            <td class="td-admin-direita">${item.value}</td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
        </g:each>
    </g:if>
</div>

</body>
</html>