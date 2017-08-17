package sistemaponto

import entity.FormatadorDataHora
import org.joda.time.Duration
import org.joda.time.LocalDate
import org.joda.time.LocalTime
import org.joda.time.Period
import util.UtilitarioSpring

import java.text.DecimalFormat

class PontoTagLib {
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]
    static namespace = "ponto"
    static encodeAsForTags = [tagName: [taglib:'raw']]

    /**
     *
     Tag responsável por converter um objeto para uma string amigável ao usuário
     @attr duration horario no formato Duration a ser apresentado na tela
     @attr localDate LocalDate representando uma data a ser apresentada na tela
     @attr localTime LocalTime representando um horario a ser apresentada na tela
     @attr valor double representando o salário do funcionário a ser apresentado na tela
     */
    def conversor = {attrs ->
        if(attrs.duration){
            out << FormatadorDataHora.toTime(attrs.duration as Duration)
        }else if(attrs.localDate){
            out << FormatadorDataHora.toDate(attrs.localDate as LocalDate)
        }else if(attrs.localTime){
            LocalTime lt = (attrs.localTime as LocalTime)
            Period p = new Period(lt.getHourOfDay(), lt.getMinuteOfHour(), lt.getSecondOfMinute(), lt.getMillisOfSecond())
            out << FormatadorDataHora.toTime(p.toStandardDuration())
        }else if(attrs.valor){
            DecimalFormat df = new DecimalFormat("0.##")
            out << df.format(attrs.valor as double)
        }else{
            out << '-'
        }
    }

    def menu = {
        Funcionario funcionario = UtilitarioSpring.getUsuarioLogado()
        if(funcionario?.authorities?.size()){
            for(rule in funcionario.authorities){
                if(rule.authority == "ROLE_ADMIN"){
                    out << render(template: '/home/menuAdmin')
                }
            }
            out << render(template: '/home/menuUser')
        }
    }
}
