package Challenge_4.ForoHub.domain.topico;

public record DatosListadoTopicos(
    Long id,
    String titulo,
    String mensaje,
    Boolean status,
    String curso
){
    public DatosListadoTopicos(Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getStatus(),
                topico.getCurso().getNombre());
    }
}
