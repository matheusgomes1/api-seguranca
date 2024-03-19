package seguranca.api.domain.Usuario.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PerfilUsuarioDto {
    String nome;
    String descricao;
    boolean ativo;
}
