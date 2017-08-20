<html>
<head>
    <meta name="layout" content="admin">
    <asset:stylesheet src="funcionarioLista.css"/>
    <asset:stylesheet src="requisicaoLista.css"/>
</head>
<body>
    <div class="conteiner-base-branco">
        <div id="conteiner-func-interno">
            <div id="conteudo-func-superior">
                <g:if test="${msg}">
                    <h4>${msg}</h4>
                </g:if>
                <h4>Funcionários</h4>
            </div>
            <div id="funcionario-tabela">
                <table>
                    <tr>
                        <td class="func-tabela-dado">Nome</td>
                        <td class="func-tabela-dado">Salário</td>
                        <td class="func-tabela-dado">Adimissão</td>
                        <td class="func-tabela-dado">Usuário</td>
                    </tr>
                    <g:each in="${funcionarios}" var="funcionario">
                        <tr>
                            <td class="func-tabela-dado"><g:link class="tabela-td-link" controller="funcionario" action="formulario" id="${funcionario.id}">
                                ${funcionario.nome}</g:link></td>
                            <td class="func-tabela-dado"><g:link class="tabela-td-link" controller="funcionario" action="formulario" id="${funcionario.id}">
                                ${funcionario.salario}</g:link></td>
                            <td class="func-tabela-dado"><g:link class="tabela-td-link" controller="funcionario" action="formulario" id="${funcionario.id}">
                                <ponto:conversor localDate="${funcionario.dataAdmissao}"/></g:link></td>
                            <td class="func-tabela-dado"><g:link class="tabela-td-link" controller="funcionario" action="formulario" id="${funcionario.id}">
                                ${funcionario.username}</g:link></td>
                        </tr>
                    </g:each>
                </table>
            </div>
            <div id="conteudo-func-inferior">
                <g:link class="btn btn-default" controller="funcionario" action="formulario">Novo</g:link>
                <p>${funcionarios.size()} funcionários - <ponto:somar items="${funcionarios*.salario}"/> R$ de salários</p>
            </div>
        </div>
    </div>
</body>
</html>




String nome
double salario
LocalDate dataAdmissao
String username
boolean isAdmin