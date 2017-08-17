// Place your Spring DSL code here
import entity.CalculadorSalarioHoraPorHora
import entity.ConfiguracaoService
import entity.SeletorCargaHoraria
import entity.PreparadorHistorico
import entity.SeletorMes
import entity.DefinidorTipoHora

beans = {

    seletorCargaHoraria(SeletorCargaHoraria){bean ->
        bean.autowire = true
    }

    preparadorHistorico(PreparadorHistorico){ bean ->
        bean.autowire = true
    }

    seletorMes(SeletorMes){bean ->
        bean.autowire = true
    }

    definidorTipoHora(DefinidorTipoHora){ bean ->
        bean.autowire = true
    }

    calculadorSalario(CalculadorSalarioHoraPorHora){ bean ->
        bean.autowire = true
    }

    configuracaoService(ConfiguracaoService){ bean ->
        bean.autowire = true
    }
}
