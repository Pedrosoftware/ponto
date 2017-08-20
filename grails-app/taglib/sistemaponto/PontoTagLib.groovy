package sistemaponto

import entity.Dia
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
    static encodeAsForTags = [tagName: [taglib: 'raw']]
    RequisicaoService requisicaoService

    /**
     *
     Tag responsável por converter um objeto para uma string amigável ao usuário
     @attr duration horario no formato Duration a ser apresentado na tela
     @attr localDate LocalDate representando uma data a ser apresentada na tela
     @attr localTime LocalTime representando um horario a ser apresentada na tela
     @attr valor double representando o salário do funcionário a ser apresentado na tela
     @attr date Date representando o mês e ano para ser apresentado na tela com mês em literal
     */
    def conversor = { attrs ->
        if (attrs.duration) {
            out << FormatadorDataHora.toTime(attrs.duration as Duration)
        } else if (attrs.localDate) {
            out << FormatadorDataHora.toDate(attrs.localDate as LocalDate)
        } else if (attrs.localTime) {
            LocalTime lt = (attrs.localTime as LocalTime)
            Period p = new Period(lt.getHourOfDay(), lt.getMinuteOfHour(), lt.getSecondOfMinute(), lt.getMillisOfSecond())
            out << FormatadorDataHora.toTime(p.toStandardDuration())
        } else if (attrs.valor) {
            DecimalFormat df = new DecimalFormat("0.##")
            out << df.format(attrs.valor as double)
        }else if (attrs.date) {
            LocalDate ld = LocalDate.fromDateFields(attrs.date as Date)
            out << FormatadorDataHora.toMonthYear(ld)
        } else {
            out << '-'
        }
    }

    def menu = {
        Funcionario funcionario = UtilitarioSpring.getUsuarioLogado()
        if (funcionario?.authorities?.size()) {
            for (rule in funcionario.authorities) {
                if (rule.authority == "ROLE_ADMIN") {
                    out << render(template: '/home/menuAdmin')
                }
            }
            out << render(template: '/home/menuUser')
        }
    }

    def botaoAdmin = {
        if (UtilitarioSpring.getUsuarioLogado().isAdmin) {
            out << '<a id="botao-admin" href="/SistemaPonto/admin/home">administrar</a>'
        }
    }

    /**
     *
     Tag responsável por definir como será a apresentação da célula de ajuste da tabela de resumo dos pontos do mês corrente
     @attr dia Objeto Dia indicando o dia da solicitação de ajuste
     @attr funcionário referente a solicitação
     */
    def solicitacaoAjuste = { attrs ->
        if (attrs.dia && attrs.funcionario) {
            Dia dia = attrs.dia
            String cssClass = "ajuste"
            Requisicao req = requisicaoService.buscarRequisicao(dia.data, attrs.funcionario as Funcionario)
            if (req) {
                if (req.isIsFinalizada()) {
                    if (req.isAprovada) {
                        cssClass = "ajuste-aceito"
                    } else {
                        cssClass = "ajuste-recusado"
                    }
                } else {
                    cssClass = "ajuste-aberto"
                }
            } else {
                if (dia.data == new LocalDate()) {
                    out << '<p class="'+cssClass+'">A</p>'
                    return
                }
                out << '<a class="'+cssClass+'" href="/SistemaPonto/requisicao?data='+FormatadorDataHora.toDate(dia.data as LocalDate)+'">A</a>'
                return
            }
            out << '<p class="'+cssClass+'">A</p>'
            return
        }
    }
}
