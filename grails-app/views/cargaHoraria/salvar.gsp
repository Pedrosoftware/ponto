<html>
<head>
    <meta name="layout" content="admin">
    <asset:stylesheet src="cargaDetalhe.css"/>
</head>

<body>
    <div class="conteiner-base-branco">
        <div id="conteiner-carga-det-interno">
            <g:if test="${carga}"><h4>Edição</h4></g:if>
            <g:else><h4>Cadastro</h4></g:else>
            <g:form controller="cargaHoraria" action="salvar">
                <input type="hidden" id="id" value="${carga?.id}" name="cargaHoraria.id" />
                <table>
                    <tr>
                        <td class="tab-carga-det-td"> <label for="descricao">Descrição</label></td>
                        <td class="tab-carga-det-td"> <input class="form-control" id="descricao" value="${carga?.descricao}" name="cargaHoraria.descricao" /></td>
                    </tr>
                    <tr>
                        <td class="tab-carga-det-td"> <label for="segunda">Segunda</label></td>
                        <td class="tab-carga-det-td"> <input class="form-control" type="number" id="segunda" value="${carga?.segunda}" name="cargaHoraria.segunda" /></td>
                    </tr>
                    <tr>
                        <td class="tab-carga-det-td"> <label for="terca">Terça</label></td>
                        <td class="tab-carga-det-td"> <input class="form-control" type="number" id="terca" value="${carga?.terca}" name="cargaHoraria.terca" /></td>
                    </tr>
                    <tr>
                        <td class="tab-carga-det-td"> <label for="quarta">Quarta</label></td>
                        <td class="tab-carga-det-td"> <input class="form-control" type="number" id="quarta" value="${carga?.quarta}" name="cargaHoraria.quarta" /></td>
                    </tr>
                    <tr>
                        <td class="tab-carga-det-td"> <label for="quinta">Quinta</label></td>
                        <td class="tab-carga-det-td"> <input class="form-control" type="number" id="quinta" value="${carga?.quinta}" name="cargaHoraria.quinta" /></td>
                    </tr>
                    <tr>
                        <td class="tab-carga-det-td"> <label for="sexta">Sexta</label></td>
                        <td class="tab-carga-det-td"> <input class="form-control" type="number" id="sexta" value="${carga?.sexta}" name="cargaHoraria.sexta" /></td>
                    </tr>
                    <tr>
                        <td class="tab-carga-det-td"> <label for="sabado">Sábado</label></td>
                        <td class="tab-carga-det-td"> <input class="form-control" type="number" id="sabado" value="${carga?.sabado}" name="cargaHoraria.sabado" /></td>
                    </tr>
                    <tr>
                        <td class="tab-carga-det-td"> <label for="domingo">Domingo</label></td>
                        <td class="tab-carga-det-td"> <input class="form-control" type="number" id="domingo" value="${carga?.domingo}" name="cargaHoraria.domingo" /></td>
                    </tr>
                    <tr>
                        <td class="tab-carga-det-td"> <g:if test="${carga?.id}"><g:link class="btn btn-link" controller="cargaHoraria" action="excluir" id="${carga?.id}">excluir</g:link></g:if></td>
                        <td class="tab-carga-det-td"> <input class="btn btn-link" type="submit" value="enviar"></td>
                    </tr>
                </table>

            </g:form>
        </div>
    </div>
</body>
</html>