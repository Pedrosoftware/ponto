<div id="seletor-mes">
    <g:form controller="${controller}" action="${action}">
        <label for="mesSelecionado">Selecione um mês:</label>
        <g:datePicker name="mesSelecionado" precision="month" default="${mesSelecionado}"/>
        <g:if test="${funcionarios}"></br>
            <label for="idFuncionario">Selecione um funcionário:</label>
            <g:select name="idFuncionario"
                      from="${funcionarios}"
                      optionKey="id"
                      optionValue="nome"
                      value="${funcionario?.id}"/>
        </g:if>
        <input class="btn-default" type="submit" value="gerar"/>
    </g:form>
</div>