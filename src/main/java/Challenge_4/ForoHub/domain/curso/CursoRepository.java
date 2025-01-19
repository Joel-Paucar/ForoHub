package Challenge_4.ForoHub.domain.curso;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    boolean existsByNombre(String curso);

    Curso findByNombre(String curso);
}
