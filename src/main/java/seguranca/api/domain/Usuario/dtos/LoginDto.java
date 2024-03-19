package seguranca.api.domain.Usuario.dtos;

import jakarta.validation.constraints.NotBlank;

public record LoginDto(
        @NotBlank
        String login,
        @NotBlank
        String senha) {
}
