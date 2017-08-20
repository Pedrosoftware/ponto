<div class="bater-ponto conteiner-base-branco">
    <h4>Horário</h4>

    <h1 class="relogio">00:00:00</h1>
    <g:form controller="funcionario" action="baterPonto">
        <input class="btn btn-block btn-dark" type="submit" value="bater ponto"/>
    </g:form>
    <g:if test="${msg}">
        <h4>${msg}</h4>
    </g:if>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        var currentdate = new Date();

        function atualizaRelogio() {
            currentdate = new Date();
            var hora = currentdate.getHours() < 10 ? "0" + currentdate.getHours() : currentdate.getHours();
            var minuto = currentdate.getMinutes() < 10 ? "0" + currentdate.getMinutes() : currentdate.getMinutes();
            var segundo = currentdate.getSeconds() < 10 ? "0" + currentdate.getSeconds() : currentdate.getSeconds();
            $('.relogio').html(hora + ":" + minuto + ":" + segundo);
        }
        atualizaRelogio();
        window.setInterval(atualizaRelogio, 1000);

    });
</script>