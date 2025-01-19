package Challenge_4.ForoHub.domain.curso;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "curso")
@Entity(name = "Curso")
@EqualsAndHashCode(of = "id")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @Enumerated(EnumType.STRING)
    private CategoriaCurso categoria;

    public Curso() {}


    public Long getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public CategoriaCurso getCategoria() {
        return categoria;
    }
}
