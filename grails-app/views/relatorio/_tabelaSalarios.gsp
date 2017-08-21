<div id="conteiner-tabela-salarios">
    <table id="tabela-salarios">
        <thead>
        <tr>
            <th class="head-tab-salario">Nome</th>
            <th class="head-tab-salario">Salário base</th>
            <th class="head-tab-salario">H.E. 50%</th>
            <th class="head-tab-salario">H.E. 100%</th>
            <th class="head-tab-salario">H.F.</th>
            <th class="head-tab-salario">Salário recebido</th>
        </tr>
        </thead>
        <tfoot>
        <tr>
            <td class="dado-tab-salario-col1 dado-tab-salario foot-tab-salario">Total:</td>
            <td class="dado-tab-salario foot-tab-salario"><ponto:somaListaValores lista="${salarios*.funcionario.salario}"/> </td>
            <td class="dado-tab-salario foot-tab-salario"><ponto:somaListaValores lista="${salarios*.valorHora50}"/> </td>
            <td class="dado-tab-salario foot-tab-salario"><ponto:somaListaValores lista="${salarios*.valorHora100}"/> </td>
            <td class="dado-tab-salario foot-tab-salario">-<ponto:somaListaValores lista="${salarios*.valorDescontado}"/> </td>
            <td class="dado-tab-salario foot-tab-salario"><ponto:somaListaValores lista="${salarios*.getTotal()}"/> </td>
        </tr>
        </tfoot>
        <tbody>
        <g:each in="${salarios}" var="${salario}">
            <tr>
                <td class="dado-tab-salario-col1 dado-tab-salario">${salario.funcionario.nome}</td>
                <td class="dado-tab-salario"><ponto:conversor valor="${salario.funcionario.salario}"/></td>
                <td class="dado-tab-salario"><ponto:conversor valor="${salario.valorHora50}"/></td>
                <td class="dado-tab-salario"><ponto:conversor valor="${salario.valorHora100}"/></td>
                <td class="dado-tab-salario"><ponto:conversor valor="${salario.valorDescontado * -1}"/></td>
                <td class="dado-tab-salario"><ponto:conversor valor="${salario.getTotal()}"/></td>
            </tr>
        </g:each>
        </tbody>
    </table>
</div>