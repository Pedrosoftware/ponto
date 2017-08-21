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
            <div class="homeadmin-bloco">
                <h4>${bloco.key}</h4>
                <table>
                    <g:each in="${bloco.value}" var="item">
                        <tr>
                            <td>${item.key}</td>
                            <td>${item.value}</td>
                        </tr>
                    </g:each>
                </table>
            </div>
        </g:each>
    </g:if>
</div>

</body>
</html>