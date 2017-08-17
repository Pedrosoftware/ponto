package entity

import org.joda.time.Duration
import org.joda.time.LocalDate
import sistemaponto.RegistroPonto

/**
 * Created by pedro on 03/08/17.
 */
class Dia {
    List<RegistroPonto> pontos
    LocalDate data
    boolean isUtil
    boolean isFeriado
    boolean isDomingo
    Duration cargaCumprida
    Duration cargaTotalDia
    TipoHoraExtra tipoHora

    Dia(){
        pontos = []
        isFeriado = false
        isDomingo = false
        isUtil = true
        cargaTotalDia = new Duration(0)
    }

    Duration getHorasExtrasCumpridas(){
        validar()
        if((cargaCumprida - cargaTotalDia).toStandardSeconds().getSeconds() > 0){
            return (cargaCumprida - cargaTotalDia)
        }
        return new Duration(0)
    }

    Duration getHorasFaltantes(){
        validar()
        if((cargaCumprida - cargaTotalDia).toStandardSeconds().getSeconds() < 0){
            return (cargaTotalDia - cargaCumprida)
        }
        return new Duration(0)
    }

    private void validar(){
        if(!cargaCumprida || !cargaTotalDia){
            throw new Exception("cargaCumprida ou cargaTotalDia não foram setadas")
        }
    }
}
