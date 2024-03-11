package clinica

import grails.gorm.services.Service

@Service(Consultorio)
interface ConsultorioService {

    Consultorio get(Serializable id)

    List<Consultorio> list(Map args)

    Long count()

    void delete(Serializable id)

    Consultorio save(Consultorio consultorio)

}