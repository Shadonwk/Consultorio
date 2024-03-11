package clinica

import java.sql.Date
import java.sql.Time
import java.time.LocalDateTime


class Cita {

    Consultorio consultorio
    Doctor doctor
    java.util.Date fechaHora
    Paciente paciente

    static constraints = {
        doctor()
        consultorio()
        paciente()

    }
}
