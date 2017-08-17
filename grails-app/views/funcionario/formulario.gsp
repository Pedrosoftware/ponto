<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <style type="text/css" media="screen">

    #controller-list ul {
        list-style-position: inside;
    }

    #controller-list li {
        line-height: 1.3;
        list-style-position: inside;
        margin: 0.25em 0;
    }

    #page-body {
        margin: 2em 1em 1.25em 18em;
    }

    </style>
</head>

<body>

<div id="page-body" role="main">
    <div id="controller-list" role="navigation">
        <g:if test="${msg}">
            <h1>${msg}</h1>
        </g:if>
        <g:form controller="funcionario" action="cadastro">
            <label for="nome">Nome:
                <input id="nome" name="funcionario.nome" type="text" value="${funcionario?.nome}"/>
            </label></br>
            <label for="salario">Salário:
                <input id="salario" name="funcionario.salario" type="text" value="${funcionario?.salario}"/>
            </label></br>
            <label>Data de admissão:
            <g:datePicker id="dataAdmissao" name="dataAdmissao" precision="day" value="${funcionario?.dataAdmissao}"/>
            </label></br>
            <label for="usuario">Usuário:
                <input id="usuario" name="funcionario.username" type="text" value="${funcionario?.username}"/>
            </label></br>
            <label for="senha">Senha:
                <input id="senha" name="funcionario.password" type="password" value="${funcionario?.password}"/>
            </label></br>
            <label for="isAdmin">
                <input id="isAdmin" name="funcionario.isAdmin" type="checkbox"/>Administrador
            </label></br>
            <g:select name="funcionario.cargaHoraria"
                      from="${cargaHoraria}"
                      optionKey="id"
                      optionValue="descricao"
                      value="${funcionario?.cargaHoraria?.id}"/>
            </br>
            <input type="submit"></br>
        </g:form>
    </div>
</div>
</body>
</html>