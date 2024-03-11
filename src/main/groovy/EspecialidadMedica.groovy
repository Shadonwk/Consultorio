package enums

enum EspecialidadMedica {

    CARDIOLOGIA (0,"especialidad.medica.cardiologia","cardiologia"),
    DERMATOLOGIA(0,"especialidad.medica.dermatologia","dermatologia"),
    ENDOCRINOLOGIA(0,"especialidad.medica.endocrinologia","endocrinologia"),
    GASTROENTEROLOGIA(0,"especialidad.medica.gastroentterologia","gastroenterologia"),
    NEUROLOGIA(0,"especialidad.medica.neurologia","neurologia"),
    OFTALMOLOGIA(0,"especialidad.medica.oftalmologia","oftalmologia")

    final int id;
    final String messageCode;
    final String code;

    private EspecialidadMedica(int id, String messageCode, String code)
    {
        this.id = id;
        this.messageCode = messageCode
        this.code = code
    }

}