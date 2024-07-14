package med.voll.api2.domain.Pregunta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreguntaRepository extends JpaRepository<ClaseDatosNuevaPregunta,Long> {
    Page<ClaseDatosNuevaPregunta> findByActivoTrue(Pageable paginacion);
}
