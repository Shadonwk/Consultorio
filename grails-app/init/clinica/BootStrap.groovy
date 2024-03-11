package clinica

import enums.EspecialidadMedica

class BootStrap {

    def init = { servletContext ->

        TimeZone.setDefault(TimeZone.getTimeZone("America/Mexico_City"))
        createDoctors()
        createConsultorios()
        createPacientes()
    }
    def destroy = {
    }

    private void createConsultorios() {
        1.upto(10) { index ->
            def consultorio = new Consultorio(
                    numero: "C${index}",
                    piso: (index % 5) + 1 // Asigna valores de piso de 1 a 5 en un ciclo
            )
            consultorio.save(flush: true)
        }
    }

    private void createPacientes() {
        def nombres = ["Ana", "Carlos", "Elena", "David", "Laura", "Miguel", "Sofía", "Javier", "Isabel", "Diego"]
        def apellidosPaterno = ["García", "Martínez", "López", "Fernández", "Rodríguez", "Pérez", "González", "Hernández", "Díaz", "Muñoz"]
        def apellidosMaterno = ["Ramírez", "Cruz", "Ortiz", "Torres", "Núñez", "Castillo", "Vargas", "Dominguez", "Gutierrez", "Fuentes"]

        1.upto(10) { index ->
            def paciente = new Paciente(
                    nombre: nombres[index - 1],
                    apellidoPaterno: apellidosPaterno[index - 1],
                    apellidoMaterno: apellidosMaterno[index - 1]
            )
            paciente.save(flush: true)
        }
    }

    private void createDoctors() {
        def nombres = ["Juan", "Maria", "Pedro", "Ana", "Luis", "Laura", "Carlos", "Elena", "Ricardo", "Isabel"]
        def apellidosPaterno = ["Gomez", "Rodriguez", "Fernandez", "Martinez", "Lopez", "Perez", "Gonzalez", "Sanchez", "Ramirez", "Diaz"]
        def apellidosMaterno = ["Lara", "Mendoza", "Castillo", "Dominguez", "Vargas", "Salazar", "Hernandez", "Gutierrez", "Guerrero", "Fuentes"]
        def especialidades = EspecialidadMedica.values()

        1.upto(10) { index ->
            def doctor = new Doctor(
                    nombre: nombres[index - 1],
                    apellidoPaterno: apellidosPaterno[index - 1],
                    apellidoMaterno: apellidosMaterno[index - 1],
                    especialidadMedica: especialidades[index % especialidades.length]
            )
            doctor.save(flush: true)
        }
    }
}
