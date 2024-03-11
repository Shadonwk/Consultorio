package clinica

class Paciente {

    String nombre
    String apellidoPaterno
    String apellidoMaterno

    static constraints = {
    }

    String  toString(){
        "$apellidoPaterno $apellidoMaterno $nombre"
    }
}
