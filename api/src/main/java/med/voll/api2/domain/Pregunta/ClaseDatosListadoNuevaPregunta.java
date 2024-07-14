package med.voll.api2.domain.Pregunta;

public record ClaseDatosListadoNuevaPregunta(Long id,String nombre,String duda,String tipo) {

    public ClaseDatosListadoNuevaPregunta(ClaseDatosNuevaPregunta pregunta){
        this(pregunta.getIdPregunta(),pregunta.getNombreUsuario(), pregunta.getDuda(), pregunta.getTipoDeDuda().toString());
    }

}
