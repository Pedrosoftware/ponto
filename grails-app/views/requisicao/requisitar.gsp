<head>
    <meta name="layout" content="main">
</head>

<body>
<div id="container-requisicao-form" class="conteiner-base-branco">
    <g:if test="${msg}">
        <h1>${msg}</h1>
    </g:if>
    <g:form id="requisicao-form" controller="Requisicao" action="requisitar">
        <h4>Solicitação ajuste de ponto</h4>
        <label>${data}</label></br>
        <div>
            <input type="hidden" name="diaRequisitado" value="${data}"/>
            <table>
                <thead>
                <tr>
                    <td>entradas</td>
                    <td>saídas</td>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td class="requisicao-td-entradas"><input class="form-control" name="horarios.entrada01"/></td>
                    <td class="requisicao-td-saidas"><input class="form-control" name="horarios.saida01"/></td>
                </tr>
                <tr>
                    <td class="requisicao-td-entradas"><input class="form-control" name="horarios.entrada02"/></td>
                    <td class="requisicao-td-saidas"><input class="form-control" name="horarios.saida02"/></td>
                </tr>
                </tbody>
            </table>
            <label for="justificativa">Justificativa</label>
            <g:textArea id="requisicao-justificativa" rows="3" class="form-control" name="justificativa"/>
        </div>
        <input class="btn btn-dark" type="submit" value="solicitar"/>
    </g:form>
</div>
</body>