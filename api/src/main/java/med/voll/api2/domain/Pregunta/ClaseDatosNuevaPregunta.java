package med.voll.api2.domain.Pregunta;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Table(name = "Pregunta")
@Entity(name = "Pregunta")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ClaseDatosNuevaPregunta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String duda;
    @Enumerated(EnumType.STRING)
    private TipoDeDuda tipo;
    private Boolean activo;

    public ClaseDatosNuevaPregunta(DatosNuevaPregunta datosPreguntaForo) {
        this.duda = datosPreguntaForo.duda();
        this.nombre = datosPreguntaForo.nombre();
        this.tipo = datosPreguntaForo.tipo();
        this.activo = true;
    }

    public Long getIdPregunta() {
        return id;
    }

    public void setIdPregunta(Long idPregunta) {
        this.id = idPregunta;
    }

    public String getNombreUsuario() {
        return nombre;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombre = nombreUsuario;
    }

    public String getDuda() {
        return duda;
    }

    public void setDuda(String duda) {
        this.duda = duda;
    }

    public TipoDeDuda getTipoDeDuda() {
        return tipo;
    }

    public void setTipoDeDuda(TipoDeDuda tipoDeDuda) {
        this.tipo = tipoDeDuda;
    }

    public void actualizarDatos(ClaseDatosActualizarPregunta claseDatosActualizarPregunta) {
        if(claseDatosActualizarPregunta.duda() != null)
            this.duda = claseDatosActualizarPregunta.duda();
        if(claseDatosActualizarPregunta.tipo() != null)
            this.tipo = claseDatosActualizarPregunta.tipo();
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public void desactivarPregunta() {
        this.activo = false;
    }
}
