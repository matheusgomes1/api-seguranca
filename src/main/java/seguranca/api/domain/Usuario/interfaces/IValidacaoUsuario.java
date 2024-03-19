package seguranca.api.domain.Usuario.interfaces;

import seguranca.api.domain.Usuario.dtos.CadastroUsuarioDto;

public interface IValidacaoUsuario {
    void Validar(CadastroUsuarioDto dto);
}
