package Challenge_4.ForoHub.domain.topico;

import Challenge_4.ForoHub.domain.curso.Curso;
import Challenge_4.ForoHub.domain.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "topico")
@Entity(name = "Topico")
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private Boolean status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario usuario;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    public Topico() {}

    public Topico(Long id, String titulo, String mensaje, LocalDateTime fechaCreacion, Boolean status, Usuario usuario, Curso curso) {
        this.id = id;
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.fechaCreacion = fechaCreacion;
        this.status = status;
        this.usuario = usuario;
        this.curso = curso;
    }

    public Long getId() {
        return id;
    }
    public String getTitulo() {
        return titulo;
    }
    public String getMensaje() {
        return mensaje;
    }
    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }
    public Boolean getStatus() {
        return status;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public Curso getCurso() {
        return curso;
    }

    public void actualizar(String titulo, String mensaje, Curso curso) {
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.curso = curso;
    }
}
