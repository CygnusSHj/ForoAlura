package med.voll.api2.domain.Pregunta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosNuevaPregunta(
        @NotBlank
        String nombre,
        @NotBlank
        String duda,
        @NotNull
        TipoDeDuda tipo
) {
}
