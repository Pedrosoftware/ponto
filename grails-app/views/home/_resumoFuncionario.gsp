<div class="resumo-funcionario conteiner-base-branco">
    <h4>Informações</h4>

    <p><span>Nome:</span> ${relatorio.funcionario.nome}</p>

    <p><span>Salário:</span> R$ ${relatorio.funcionario.salario}</p>

    <p><span>Admissão:</span> <ponto:conversor localDate="${relatorio.funcionario.dataAdmissao}"/></p>

    <p><span>Horas extras:</span> <ponto:conversor
            duration="${relatorio.horasExtras50 + relatorio.horasExtras100}"/></p>

    <p><span>Horas faltantes:</span> <ponto:conversor duration="${relatorio.horasFaltantes}"/></p>
</div>