package sistemaponto

import org.joda.time.LocalDate

class Funcionario {

    transient springSecurityService

    String nome
    double salario
    LocalDate dataAdmissao
    String username
    String password
    boolean isAdmin
    CargaHoraria cargaHoraria
    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired

    static constraints = {

        nome nullable: false, blank: false, maxSize: 100
        username nullable: false, blank: false, maxSize: 100, unique: true
        password nullable: false, blank: false
        dataAdmissao nullable: false
    }
    //TODO tenho que adicionar o construtor com o this no começo talvez

    String toString(){
        return nome
    }


    Set<Regra> getAuthorities() {
        FuncionarioRegra.findAllByFuncionario(this)*.regra
    }

    def beforeInsert() {
        encodePassword()
    }

    def beforeUpdate() {
        if (isDirty('password')) {
            encodePassword()
        }
    }

    protected void encodePassword() {
        password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
    }

    static transients = ['springSecurityService']

    static mapping = {
        password column: '`password`'
    }

}
