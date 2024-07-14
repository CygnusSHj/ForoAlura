package med.voll.api2.domain.Pregunta;

public record RetornaPregunta(
        Long id,
         String nombre,
         String duda,
        TipoDeDuda tipo
) {
}
