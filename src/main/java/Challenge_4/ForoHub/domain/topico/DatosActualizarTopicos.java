package Challenge_4.ForoHub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopicos(
    @NotBlank String titulo,
    @NotBlank String mensaje,
    @NotNull String curso
) {
}
