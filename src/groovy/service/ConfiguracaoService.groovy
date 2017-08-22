package service

import net.sf.cglib.core.Local
import org.joda.time.LocalDate

class ConfiguracaoService {

    static final String DIA_FECHAMENTO = "diaFechamento"

    static String getConfig(String key){
        for(item in getMap()){
            if(item.key == key){
                return item.value
            }
        }
        throw new Exception("Propriedade não encontrada")
    }

    static void escrever(String key, String value){
        println "Vou escrever em mapa[${key}] = ${value}"
        Map mapa = getMap()
        mapa[key] = value
        escrever(mapa)
    }

    static void escrever(Map mapa){
        mapa = mapa.sort{it.key}
        FileWriter fr = new FileWriter(getFile())
        for(dado in mapa) {
            println "escrevendo::: ${dado.key}=${dado.value}\n"
            fr.write("${dado.key}=${dado.value}\n")
        }
        fr.close()
    }

    static Map getMap() {
        Map mapa = [:]
        FileReader fr = new FileReader(getFile())
        BufferedReader br = new BufferedReader(fr)
        String linha
        String[] vetor
        while ((linha = br.readLine()) != null) {
            vetor = linha.split("=")
            mapa[vetor[0]] = vetor[1]
        }
        fr.close()
        return mapa
    }

    static LocalDate getUltimoDiaFechamento(){
        LocalDate date = new LocalDate()
        date = getDiaFechamento(date.getMonthOfYear(), date.getYear())
        if(date > new LocalDate()){
            date = date.minusMonths(1)
        }
        return date
    }

    static LocalDate getDiaFechamento(){
        LocalDate date = new LocalDate()
        return getDiaFechamento(date.getMonthOfYear(), date.getYear())
    }

    static LocalDate getDiaFechamento(int mes, int ano){
        int dia = getConfig(DIA_FECHAMENTO) as int
        LocalDate date = new LocalDate(ano, mes, 1)
        date = date.withDayOfMonth(dia)
        return date
    }

    static LocalDate getDiaAbertura(int mes, int ano){
        LocalDate fechamento = getDiaFechamento(mes, ano)
        return fechamento.minusMonths(1).plusDays(1)
    }

    private static File getFile(){
        File file = new File("configuracao.conf")
        file.createNewFile()
        return file
    }
}
