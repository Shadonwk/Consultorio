package clinica

import enums.EspecialidadMedica

class Doctor {

    String nombre
    String apellidoPaterno
    String apellidoMaterno
    EspecialidadMedica especialidadMedica


    static constraints = {
    }

    String  toString(){
        "$apellidoPaterno $apellidoMaterno $nombre"
    }
}
