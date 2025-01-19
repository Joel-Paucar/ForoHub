package Challenge_4.ForoHub.domain.topico;

import java.time.LocalDateTime;


public record DatosRespuestaTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        String autor,
        String curso
) {
    public DatosRespuestaTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(),
                topico.getUsuario().getNombre(), topico.getCurso().getNombre());
    }
}
