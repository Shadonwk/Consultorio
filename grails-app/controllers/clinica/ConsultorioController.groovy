package clinica

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ConsultorioController {

    ConsultorioService consultorioService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond consultorioService.list(params), model:[consultorioCount: consultorioService.count()]
    }

    def show(Long id) {
        respond consultorioService.get(id)
    }

    def create() {
        respond new Consultorio(params)
    }

    def save(Consultorio consultorio) {
        if (consultorio == null) {
            notFound()
            return
        }

        try {
            consultorioService.save(consultorio)
        } catch (ValidationException e) {
            respond consultorio.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'consultorio.label', default: 'Consultorio'), consultorio.id])
                redirect consultorio
            }
            '*' { respond consultorio, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond consultorioService.get(id)
    }

    def update(Consultorio consultorio) {
        if (consultorio == null) {
            notFound()
            return
        }

        try {
            consultorioService.save(consultorio)
        } catch (ValidationException e) {
            respond consultorio.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'consultorio.label', default: 'Consultorio'), consultorio.id])
                redirect consultorio
            }
            '*'{ respond consultorio, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        consultorioService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'consultorio.label', default: 'Consultorio'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'consultorio.label', default: 'Consultorio'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
