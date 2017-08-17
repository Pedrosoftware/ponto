package sistemaponto

import grails.transaction.Transactional

@Transactional
class FuncionarioService {

    Map salvar(Funcionario funcionario){
        Map model = [:]
        model['msg'] = ""
        if (funcionario.save()) {
            model['msg'] = "funcionário inserido com sucesso"
        } else {
            funcionario.errors.allErrors.each { model['msg'] += it }
        }
        return model
    }

    List<Funcionario> listar(){
        return Funcionario.list()
    }

    Funcionario get(int id){
        return Funcionario.get(id)
    }
}
