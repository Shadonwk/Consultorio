package clinica

import grails.gorm.services.Service

@Service(Paciente)
interface PacienteService {

    Paciente get(Serializable id)

    List<Paciente> list(Map args)

    Long count()

    void delete(Serializable id)

    Paciente save(Paciente paciente)

}