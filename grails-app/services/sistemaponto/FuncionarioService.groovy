package sistemaponto

import grails.transaction.Transactional

@Transactional
class FuncionarioService {

    Funcionario salvar(Funcionario funcionario){
        Map model = [:]
        model['msg'] = ""
        return funcionario.save()
    }

    List<Funcionario> listar(){
        List<Funcionario> lista = Funcionario.findAll()
        lista.sort{it.nome.toLowerCase()}
        return lista
    }

    Funcionario get(int id){
        return Funcionario.get(id)
    }
}
