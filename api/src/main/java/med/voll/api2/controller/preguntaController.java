package med.voll.api2.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api2.domain.Pregunta.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/pregunta")

public class preguntaController {

    @Autowired
    private PreguntaRepository preguntaRepository;

    @PostMapping
    public ResponseEntity<RetornaPregunta> nuevaPregunta(@RequestBody @Valid DatosNuevaPregunta DatosPreguntaForo,
                                                         UriComponentsBuilder uriComponentsBuilder){
        ClaseDatosNuevaPregunta pregunta = preguntaRepository.save(new ClaseDatosNuevaPregunta(DatosPreguntaForo));
        //retorn 201
        RetornaPregunta datosPregunta = new RetornaPregunta(pregunta.getIdPregunta(), pregunta.getNombreUsuario(),
                pregunta.getDuda(), pregunta.getTipoDeDuda());
        URI url = uriComponentsBuilder.path("/pregunta/{ide}").buildAndExpand(pregunta.getIdPregunta()).toUri();
        return ResponseEntity.created(url).body(datosPregunta);
    }

    @GetMapping
    public ResponseEntity<Page<ClaseDatosListadoNuevaPregunta>> listado(Pageable paginacion){
       // preguntaRepository.findAll(paginacion).map(ClaseDatosListadoNuevaPregunta::new);
        return ResponseEntity.ok(preguntaRepository.findByActivoTrue(paginacion).map(ClaseDatosListadoNuevaPregunta::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarPregunta(@RequestBody @Valid ClaseDatosActualizarPregunta claseDatosActualizarPregunta){
        ClaseDatosNuevaPregunta pregunta = preguntaRepository.getReferenceById(claseDatosActualizarPregunta.id());
        pregunta.actualizarDatos(claseDatosActualizarPregunta);
        return ResponseEntity.ok(new RetornaPregunta(pregunta.getIdPregunta(), pregunta.getNombreUsuario(),
                pregunta.getDuda(), pregunta.getTipoDeDuda()));
    }

    @DeleteMapping("/{ide}")
    @Transactional
    public ResponseEntity eliminarPregunta(@PathVariable Long ide){
        //borrado "fisico"
        ClaseDatosNuevaPregunta pregunta = preguntaRepository.getReferenceById(ide);
       // preguntaRepository.delete(pregunta);
        //Borrado "Logico"
        pregunta.desactivarPregunta();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{ide}")
    public ResponseEntity<RetornaPregunta> retornaDatosMedico(@PathVariable Long ide){
        ClaseDatosNuevaPregunta pregunta = preguntaRepository.getReferenceById(ide);
        var datosPregunta = new RetornaPregunta(pregunta.getIdPregunta(), pregunta.getNombreUsuario(),
                pregunta.getDuda(), pregunta.getTipoDeDuda());
        return ResponseEntity.ok(datosPregunta);
    }

}
