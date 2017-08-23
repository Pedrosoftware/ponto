// Place your Spring DSL code here
import service.CalculadorSalarioHoraPorHora
import service.ConfiguracaoService
import service.SeletorCargaHoraria
import service.PreparadorHistorico
import service.SeletorMes
import service.DefinidorTipoHora

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
