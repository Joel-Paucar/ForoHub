package Challenge_4.ForoHub.domain.topico;

import Challenge_4.ForoHub.domain.ValidacionException;
import Challenge_4.ForoHub.domain.curso.Curso;
import Challenge_4.ForoHub.domain.curso.CursoRepository;
import Challenge_4.ForoHub.domain.usuario.Usuario;
import Challenge_4.ForoHub.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class OperacionesTopico {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public Page<DatosListadoTopicos> listarTopicos(Pageable paginacion){
        return topicoRepository.findByStatusTrue(paginacion).map(DatosListadoTopicos::new);
    }

    public DatosRespuestaTopico detallesTopicos(Long id){
        var topico = topicoRepository.getReferenceById(id);
        return new DatosRespuestaTopico(topico);
    }

    public DatosRespuestaTopico publicarTopico(DatosRegistroTopicos datos){
        if (!usuarioRepository.existsByNombre(datos.autor())){
            throw new ValidacionException("No existe el usuario");
        }
        if (!cursoRepository.existsByNombre(datos.curso())){
            throw new ValidacionException("No existe el curso");
        }
        var mismoTopico = topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje());
        if (mismoTopico){
            throw new ValidacionException("Ya existe ese topico");
        }
        Usuario usuario = (Usuario) usuarioRepository.findByNombre(datos.autor());
        Curso curso = cursoRepository.findByNombre(datos.curso());
        LocalDateTime fecha = LocalDateTime.now();
        Topico topico = new Topico(null, datos.titulo(), datos.mensaje(), fecha, true, usuario, curso);
        topicoRepository.save(topico);

        return new DatosRespuestaTopico(topico);
    }

    public DatosRespuestaTopico actualizarTopico(Long id, DatosActualizarTopicos datos){
        if (!topicoRepository.existsById(id)){
            throw new ValidacionException("El id proporcionado no existe");
        }
        if (!cursoRepository.existsByNombre(datos.curso())){
            throw new ValidacionException("No existe el curso");
        }
        var mismoTopico = topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje());
        if (mismoTopico){
            throw new ValidacionException("Ya existe ese topico");
        }
        Curso curso = cursoRepository.findByNombre(datos.curso());
        Topico topico = topicoRepository.getReferenceById(id);
        topico.actualizar(datos.titulo(), datos.mensaje(), curso);
        return new DatosRespuestaTopico(topico);
    }

    public Object eliminarTopico(Long id) {
        if (!topicoRepository.existsById(id)){
            throw new ValidacionException("El id proporcionado no existe");
        }
        var topico = topicoRepository.getReferenceById(id);
        topicoRepository.deleteById(id);
        return new DatosRespuestaTopico(topico);
    }
}
