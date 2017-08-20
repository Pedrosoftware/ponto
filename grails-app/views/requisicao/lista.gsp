<html>
<head>
    <meta name="layout" content="admin">
    <asset:stylesheet src="requisicaoLista.css"/>
</head>

<body>
<div class="conteiner-base-branco">
    <g:if test="${msg}">
        <h4>${msg}</h4>
    </g:if>
    <h4 id="requisicao-lista-titulo">Lista de requisições em aberto</h4>
    <table class="tabela-requisicoes">
        <tr>
            <td class="dado-tabela">data Solicitação</td>
            <td class="dado-tabela">Dia Requisitado</td>
            <td class="dado-tabela">Requisitante</td>
        </tr>
        <g:each var="requisicao" in="${requisicoes}">

            <tr class='tabela-linha'>
                <td class="dado-tabela">
                    <g:link class="tabela-td-link" controller="requisicao" action="detalhe" id="${requisicao.id}">
                        <ponto:conversor localDate="${requisicao.dataSolicitacao}"/>
                    </g:link></td>
                <td class="dado-tabela">
                    <g:link class="tabela-td-link" controller="requisicao" action="detalhe" id="${requisicao.id}">
                        <ponto:conversor localDate="${requisicao.diaRequisitado}"/>
                    </g:link></td>
                <td class="dado-tabela">
                    <g:link class="tabela-td-link" controller="requisicao" action="detalhe" id="${requisicao.id}">
                        ${requisicao.funcionario.nome}
                    </g:link></td>
            </tr>
        </g:each>
    </table>
</div>
</body>
</html>