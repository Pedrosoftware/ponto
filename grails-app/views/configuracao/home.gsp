<head>
    <meta name="layout" content="admin">
    <asset:stylesheet src="configuracao.css"/>
</head>

<body>
<div class="conteiner-base-branco">
    <div id="conteudo-config-interior">
        <g:if test="${msg}">
            <h4>${msg}</h4>
        </g:if>
        <h4>Configurações</h4>
        <div id="configuracao">
            <div id="config-feriados">
                <div>
                    <p>Feriados do período</p>
                    <g:each in="${feriados}" var="feriado">
                        <p><ponto:conversor localDate="${feriado.data}"/></p>
                    </g:each>
                    <g:form controller="feriado" action="cadastrar">
                        <label>cadastrar</br><g:datePicker name="dia" precision="day" default="${diaInformado}"/></label></br>
                        <input class="btn btn-dark form-control" type="submit" value="novo feriado"/>
                    </g:form>
                </div>
            </div>

            <div id="config-config">
                <g:form controller="configuracao" action="configurar">
                    <label for="dia">Dia de fechamento do mês</label></br>
                    <input class="form-control" type="text" name="diaFechamento" id="dia" value="${diaFechamento}"/></br>
                    <input class="btn btn-dark form-control" type="submit" value="alterar dia"/>
                </g:form>
            </div>
        </div>
    </div>
</div>
</body>