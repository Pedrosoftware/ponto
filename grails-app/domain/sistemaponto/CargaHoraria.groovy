package sistemaponto

class CargaHoraria {

    String descricao
    int segunda
    int terca
    int quarta
    int quinta
    int sexta
    int sabado
    int domingo

    static constraints = {
        segunda nullable: false,blank: false
        terca nullable: false,blank: false
        quarta nullable: false,blank: false
        quinta nullable: false,blank: false
        sexta nullable: false,blank: false
        sabado nullable: false,blank: false
        domingo nullable: false,blank: false
    }

    String toString(){
        return descricao
    }
}
