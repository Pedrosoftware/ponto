<html>
<head>
    <meta name="layout" content="admin">
    <asset:stylesheet src="requisitar.css"/>
    <asset:stylesheet src="requisicaoDetalhe.css"/>
</head>

<body>
<div class="conteiner-base-branco">
    <div id="conteiner-interno">
        <div id="conteudo-superior">
            <div id="requisicao-descricacao">
                <p><span>Nome:</span> ${requisicao.funcionario.nome}</p>

                <p><span>Data:</span> <ponto:conversor localDate="${requisicao.dataSolicitacao}"/></p>

                <p><span>Dia solicitado: </span><ponto:conversor localDate="${requisicao.diaRequisitado}"/></p>
            </div>

            <div id="requisicao-pontos">
                <p>Pontos solicitados</p>
                <table id="tabela-pontos">
                    <thead>
                    <tr>
                        <td>entradas</td>
                        <td>saídas</td>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td class="requisicao-td">
                            <span class="form-control"><ponto:conversor localTime="${horarios.get(0)}"/></span></td>
                        <td class="requisicao-td">
                            <span class="form-control"><ponto:conversor localTime="${horarios.get(1)}"/></span></td>
                    </tr>
                    <tr>
                        <td class="requisicao-td">
                            <span class="form-control"><ponto:conversor localTime="${horarios.get(2)}"/></span></td>
                        <td class="requisicao-td">
                            <span class="form-control"><ponto:conversor localTime="${horarios.get(3)}"/></span></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>

        <div id="requisicao-justificativa">
            <p><span>Justificativa</span></p>
            <p class="form-control">${requisicao.justificativa}</p></div>

        <div id="requisicao-botoes">
            <g:form class="requisicao-botao" controller="requisicao" action="recusar">
                <input type="hidden" value="${requisicao.id}" name="id"/>
                <input class="btn btn-danger" type="submit" value="recusar"/>
            </g:form>
            <g:form class="requisicao-botao" controller="requisicao" action="aprovar">
                <input type="hidden" value="${requisicao.id}" name="id"/>
                <input class="btn btn-success" type="submit" value="aprovar"/>
            </g:form>
        </div>
    </div>
</div>
</body>
</html>