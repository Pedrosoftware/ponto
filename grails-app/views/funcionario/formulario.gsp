<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="admin">
    <asset:stylesheet src="funcionarioDetalhe.css"/>
</head>

<body>
<div id="page-body" role="main">
    <div class="conteiner-base-branco">
        <div id="conteiner-func-detalhe-interno">
            <g:if test="${msg}">${msg}</g:if>
            <h4>
                <g:if test="${funcionario}">Edição</g:if>
                <g:else>Cadastro</g:else>
            </h4>
            <g:form controller="funcionario" action="salvar">
                <input type="hidden" name="funcionario.id" value="${funcionario?.id}"/>
            <table id="tabela-func-detalhe">
                <tr>
                    <td class="dado-tab-func-label">Nome</td>
                    <td class="dado-tab-func-valor"><input class="form-control" name="funcionario.nome" value="${funcionario?.nome}"/></td>
                </tr>
                <tr>
                    <td class="dado-tab-func-label">Salário</td>
                    <td class="dado-tab-func-valor"><input type="number" class="form-control" name="funcionario.salario" value="${funcionario?.salario}"/></td>
                </tr>
                <tr>
                    <td class="dado-tab-func-label">Data adminissão</td>
                    <td class="dado-tab-func-valor"><g:datePicker name="funcionario.dataAdmissao" precision="day" value="${funcionario?.dataAdmissao?.toDate()}"/></td>
                </tr>
                <tr>
                    <td class="dado-tab-func-label">Usuário</td>
                    <td class="dado-tab-func-valor"><input class="form-control" name="funcionario.username" value="${funcionario?.username}"/></td>
                </tr>
                <tr>
                    <td class="dado-tab-func-label">Senha</td>
                    <td class="dado-tab-func-valor"><input class="form-control" name="funcionario.password" type="password" value="${funcionario?.password}"/></td>
                </tr>
                <tr>
                    <td class="dado-tab-func-label">Carga horária</td>
                    <td class="dado-tab-func-valor"><g:select name="funcionario.cargaHoraria"
                                  from="${cargaHoraria}"
                                  optionKey="id"
                                  optionValue="descricao"
                                  value="${funcionario?.cargaHoraria?.id}"/></td>
                </tr>
            </table>
                <input class="btn btn-dark" type="submit" value="Salvar"></br>
            </g:form>
        </div>
    </div>

</div>
</body>
</html>