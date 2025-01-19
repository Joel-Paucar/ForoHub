package Challenge_4.ForoHub.domain.usuario;

import Challenge_4.ForoHub.domain.perfil.Perfil;

public record DatosRespuestaUsuario(
        String nombre,
        String email,
        Perfil perfil
) {
}
