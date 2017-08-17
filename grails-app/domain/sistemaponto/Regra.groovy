package sistemaponto

class Regra {

    String authority

    Regra(String authority) {
        this()
        this.authority = authority
    }

    static constraints = {
        authority blank: false, unique: true
    }

    static mapping = {
        cache true
    }
}
