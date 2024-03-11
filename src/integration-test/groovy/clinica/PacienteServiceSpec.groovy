package clinica

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class PacienteServiceSpec extends Specification {

    PacienteService pacienteService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Paciente(...).save(flush: true, failOnError: true)
        //new Paciente(...).save(flush: true, failOnError: true)
        //Paciente paciente = new Paciente(...).save(flush: true, failOnError: true)
        //new Paciente(...).save(flush: true, failOnError: true)
        //new Paciente(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //paciente.id
    }

    void "test get"() {
        setupData()

        expect:
        pacienteService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Paciente> pacienteList = pacienteService.list(max: 2, offset: 2)

        then:
        pacienteList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        pacienteService.count() == 5
    }

    void "test delete"() {
        Long pacienteId = setupData()

        expect:
        pacienteService.count() == 5

        when:
        pacienteService.delete(pacienteId)
        sessionFactory.currentSession.flush()

        then:
        pacienteService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Paciente paciente = new Paciente()
        pacienteService.save(paciente)

        then:
        paciente.id != null
    }
}
