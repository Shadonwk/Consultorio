package clinica

import grails.validation.ValidationException
import org.springframework.validation.Errors

import javax.print.Doc

import static org.springframework.http.HttpStatus.*

class CitaController {

    CitaService citaService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond citaService.list(params), model:[citaCount: citaService.count()]
    }

    def show(Long id) {
        respond citaService.get(id)
    }

    def create() {
        respond new Cita(params)
    }

    def save(Cita cita) {
        if (cita == null) {
            notFound()
            return
        }

        // Verificar si ya existe una cita en la misma fecha y hora
        def consultorio = Consultorio.get(params.consultorio.id)
        def doctor = Doctor.get(params.doctor.id)
        def paciente = Paciente.get(params.paciente.id)
        def fechaHora = params.fechaHora.clone()
        def inicioDia = params.fechaHora.clearTime()
        def finDia = inicioDia + 1

        //Un mismo doctor no puede tener más de 8 citas en el día.
        def citasMismoDoctor = Cita.findAllByDoctorAndFechaHoraBetween(doctor, inicioDia, finDia)
        if(citasMismoDoctor.size() == 8){
            flash.error = "El doctor $doctor ya tiene el maximo numero de citas para esta fecha"
            respond cita.errors,  view:'create'
            return
        }

        //No se puede agregar cita en un mismo consultorio a la misma hora
        def citasMismoConsultorio = Cita.findAllByConsultorioAndFechaHora(consultorio, fechaHora)
        if(citasMismoConsultorio){
            flash.error = "No se puede agregar cita en un mismo consultorio a la misma hora"
            respond cita.errors,  view:'create'
            return
        }
        //No se puede agendar cita para un mismo Dr. a la misma hora.
        def citasMismoDoctorHora = Cita.findAllByDoctorAndFechaHora(doctor, fechaHora)
        if(citasMismoConsultorio){
            flash.error = "No se puede agendar cita para un mismo Dr. a la misma hora."
            respond cita.errors,  view:'create'
            return
        }

        //No se puede agendar cita para un paciente a la una misma hora ni con menos de 2 horas
        //de diferencia para el mismo día. Es decir, si un paciente tiene 1 cita de 2 a 3pm, solo
        //podría tener otra el mismo día a partir de las 5pm.
        
        def calendar = Calendar.getInstance()
        calendar.time = fechaHora.clone()
        calendar.add(Calendar.HOUR_OF_DAY, -2)
        def fechaRestada = calendar.time

        def citaMismoPacienteHora = Cita.findAllByPacienteAndFechaHoraBetween(paciente, fechaRestada, fechaHora)
        if(citaMismoPacienteHora){
            flash.error = "No se puede agendar cita para un paciente a la una misma hora ni con menos de 2 horas"
            respond cita.errors,  view:'create'
            return
        }

        try {
            cita.fechaHora = fechaHora
            citaService.save(cita)
        } catch (ValidationException e) {
            respond cita.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'cita.label', default: 'Cita'), cita.id])
                redirect cita
            }
            '*' { respond cita, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond citaService.get(id)
    }

    def update(Cita cita) {
        if (cita == null) {
            notFound()
            return
        }

        try {
            citaService.save(cita)
        } catch (ValidationException e) {
            respond cita.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'cita.label', default: 'Cita'), cita.id])
                redirect cita
            }
            '*'{ respond cita, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        citaService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'cita.label', default: 'Cita'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'cita.label', default: 'Cita'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
