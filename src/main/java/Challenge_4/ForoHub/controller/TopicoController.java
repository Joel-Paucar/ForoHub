package Challenge_4.ForoHub.controller;

import Challenge_4.ForoHub.domain.topico.DatosActualizarTopicos;
import Challenge_4.ForoHub.domain.topico.DatosRegistroTopicos;
import Challenge_4.ForoHub.domain.topico.OperacionesTopico;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {
    @Autowired
    private OperacionesTopico operaciones;

    @PostMapping
    public ResponseEntity registrarTopico(@RequestBody @Valid DatosRegistroTopicos datosRegistroTopicos,
                                          UriComponentsBuilder uriComponentsBuilder){
        var respuestaTopico = operaciones.publicarTopico(datosRegistroTopicos);
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(respuestaTopico.id()).toUri();
        return ResponseEntity.ok(respuestaTopico);
    }

    @GetMapping
    public ResponseEntity listadoTopicos(@PageableDefault(size = 10, sort = "curso") Pageable paginacion){
        var listaTopicos = operaciones.listarTopicos(paginacion);
        return ResponseEntity.ok(listaTopicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity detallesTopicos(@PathVariable @Valid Long id){
        var detalleTopico = operaciones.detallesTopicos(id);
        return ResponseEntity.ok(detalleTopico);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizarTopicos(@PathVariable @Valid Long id,
                                            @RequestBody @Valid DatosActualizarTopicos datosActualizarTopicos){
        var actualizarTopico = operaciones.actualizarTopico(id, datosActualizarTopicos);
        return ResponseEntity.ok(actualizarTopico);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopicos(@PathVariable @Valid Long id){
        var eliminarTopico = operaciones.eliminarTopico(id);
        return ResponseEntity.ok("Topico eliminado exitosamente:");
    }
}