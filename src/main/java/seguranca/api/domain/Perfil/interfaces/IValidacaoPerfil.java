package seguranca.api.domain.Perfil.interfaces;

import seguranca.api.domain.Perfil.dtos.CadastroPerfilDto;

public interface IValidacaoPerfil {
    void validar(CadastroPerfilDto dto);
}
