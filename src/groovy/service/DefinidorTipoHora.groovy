package service

import entity.Dia
import entity.TipoHoraExtra

/**
 * Created by pedro on 08/08/17.
 */
class DefinidorTipoHora {

    TipoHoraExtra getTipo(Dia dia) {

        if((dia.isFeriado || dia.isDomingo) && !dia.isUtil){
            return TipoHoraExtra.HORA100
        }else{
            return TipoHoraExtra.HORA50
        }
    }
}
