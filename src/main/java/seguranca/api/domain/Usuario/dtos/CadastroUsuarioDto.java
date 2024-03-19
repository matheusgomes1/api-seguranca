package seguranca.api.domain.Usuario.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CadastroUsuarioDto(
        @NotBlank
        String nome,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String login,

        @NotBlank
        @Size(min = 6)
        String senha
) {}
