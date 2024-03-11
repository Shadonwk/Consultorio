package clinica

import grails.gorm.services.Service

@Service(Cita)
interface CitaService {

    Cita get(Serializable id)

    List<Cita> list(Map args)

    Long count()

    void delete(Serializable id)

    Cita save(Cita cita)

}