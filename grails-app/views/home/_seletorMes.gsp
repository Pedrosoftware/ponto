<div id="seletor-mes">
    <g:form controller="funcionario" action="relatorio">
        <label for="mesSelecionado">Selecione um mês:</label>
        <g:datePicker name="mesSelecionado" precision="month" default="${mesSelecionado}"/>
        <input class="btn-default" type="submit" value="gerar"/>
    </g:form>
</div>