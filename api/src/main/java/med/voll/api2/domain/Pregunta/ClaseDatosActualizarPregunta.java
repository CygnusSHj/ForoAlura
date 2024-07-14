package med.voll.api2.domain.Pregunta;

import jakarta.validation.constraints.NotNull;

public record ClaseDatosActualizarPregunta(@NotNull Long id, String duda, TipoDeDuda tipo) {
}
