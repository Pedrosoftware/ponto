<html>
<head>
    <meta name="layout" content="main">
</head>

<body>
<g:form controller="cargaHoraria" action="salvar">
    <input type="hidden" id="id" value="${carga?.id}" name="cargaHoraria.id" />
    <label for="descricao">Descrição</br>
        <input type="text" id="descricao" value="${carga?.descricao}" name="cargaHoraria.descricao" />
    </label></br>
    <label for="segunda">Segunda</br>
        <input type="number" id="segunda" value="${carga?.segunda}" name="cargaHoraria.segunda" />
        </label></br>
        <label for="terca">Terça</br>
            <input type="number" id="terca" value="${carga?.terca}" name="cargaHoraria.terca" />
        </label></br>
        <label for="quarta">Quarta</br>
            <input type="number" id="quarta" value="${carga?.quarta}" name="cargaHoraria.quarta" />
        </label></br>
        <label for="quinta">Quinta</br>
            <input type="number" id="quinta" value="${carga?.quinta}" name="cargaHoraria.quinta" />
        </label></br>
        <label for="sexta">Sexta</br>
            <input type="number" id="sexta" value="${carga?.sexta}" name="cargaHoraria.sexta" />
        </label></br>
        <label for="sabado">Sábado</br>
            <input type="number" id="sabado" value="${carga?.sabado}" name="cargaHoraria.sabado" />
        </label></br>
        <label for="domingo">Domingo</br>
            <input type="number" id="domingo" value="${carga?.domingo}" name="cargaHoraria.domingo" />
        </label></br>
    <input type="submit" value="enviar">
</g:form>
</body>
</html>