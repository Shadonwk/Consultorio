package clinica

class Consultorio {

    String numero
    Integer piso

    static constraints = {
    }

    String  toString(){
        "Piso: $piso $numero"
    }
}
