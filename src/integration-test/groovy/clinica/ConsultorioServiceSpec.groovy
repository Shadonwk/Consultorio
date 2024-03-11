package clinica

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ConsultorioServiceSpec extends Specification {

    ConsultorioService consultorioService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Consultorio(...).save(flush: true, failOnError: true)
        //new Consultorio(...).save(flush: true, failOnError: true)
        //Consultorio consultorio = new Consultorio(...).save(flush: true, failOnError: true)
        //new Consultorio(...).save(flush: true, failOnError: true)
        //new Consultorio(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //consultorio.id
    }

    void "test get"() {
        setupData()

        expect:
        consultorioService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Consultorio> consultorioList = consultorioService.list(max: 2, offset: 2)

        then:
        consultorioList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        consultorioService.count() == 5
    }

    void "test delete"() {
        Long consultorioId = setupData()

        expect:
        consultorioService.count() == 5

        when:
        consultorioService.delete(consultorioId)
        sessionFactory.currentSession.flush()

        then:
        consultorioService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Consultorio consultorio = new Consultorio()
        consultorioService.save(consultorio)

        then:
        consultorio.id != null
    }
}
