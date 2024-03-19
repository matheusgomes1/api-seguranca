package seguranca.api.domain.Usuario.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ListagemUsuarioDto {
    private String nome;
    private String email;
    private String login;
    private boolean ativo;
    private List<PerfilUsuarioDto> perfis;
}
