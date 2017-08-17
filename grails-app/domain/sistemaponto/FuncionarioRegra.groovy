package sistemaponto

import grails.gorm.DetachedCriteria
import org.apache.commons.lang.builder.HashCodeBuilder

class FuncionarioRegra implements Serializable{

    private static final long serialVersionUID = 1

    Funcionario funcionario
    Regra regra
    FuncionarioRegra(Funcionario u, Regra r) {
        this()
        funcionario = u
        regra = r
    }

    @Override
    boolean equals(other) {
        if (!(other instanceof FuncionarioRegra)) {
            return false
        }

        other.funcionario?.id == funcionario?.id && other.regra?.id == regra?.id
    }

    @Override
    int hashCode() {
        def builder = new HashCodeBuilder()
        if (funcionario) {
            builder.append(funcionario.id)
        }
        if (regra) {
            builder.append(regra.id)
        }
        builder.toHashCode()
    }

    static FuncionarioRegra get(long funcionarioId, long regraId) {
        FuncionarioRegra fr = criteriaFor(funcionarioId, regraId).get()
        return fr
    }

    private static DetachedCriteria criteriaFor(long funcionarioId, long regraId) {
        FuncionarioRegra.where {
            funcionario == Funcionario.load(funcionarioId) &&
                    regra == Regra.load(regraId)
        }
    }

    static boolean exists(long funcionario, long regra) {
        criteriaFor(funcionario, regra).count()
    }


    static FuncionarioRegra create(Funcionario funcionario, Regra regra, boolean flush = false) {
        def instance = new FuncionarioRegra(funcionario: funcionario, regra: regra)
        instance.save(flush: flush, insert: true)
        return instance
    }


    static boolean remove(Funcionario func, Regra rule, boolean flush = false) {
        if (func == null || rule == null) {
            return false
        }

        int rowCount = FuncionarioRegra.where { funcionario == func && regra == rule }.deleteAll()

        if (flush) {
            FuncionarioRegra.withSession { it.flush() }
        }

        return rowCount
    }


    static void removeAll(Funcionario func, boolean flush = false) {
        if (func == null) {
            return
        }

        FuncionarioRegra.where { funcionario == func }.deleteAll()

        if (flush) {
            FuncionarioRegra.withSession { it.flush() }
        }
    }

    static void removeAll(Regra r, boolean flush = false) {
        if (r == null) {
            return
        }

        FuncionarioRegra.where { regra == r }.deleteAll()

        if (flush) {
            FuncionarioRegra.withSession { it.flush() }
        }
    }

    static constraints = {
        regra validator: { Regra rule, FuncionarioRegra apRegra ->
            if (apRegra.funcionario == null || apRegra.funcionario.id == null) {
                return
            }
            boolean existing = false
            FuncionarioRegra.withNewSession {
                existing = FuncionarioRegra.exists(apRegra.funcionario.id, rule.id)
            }
            if (existing) {
                return 'userRole.exists'
            }
        }
    }

    static mapping = {
        id composite: ['funcionario', 'regra']
        version false
    }
}
