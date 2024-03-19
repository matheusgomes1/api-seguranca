package seguranca.api.domain.Perfil.interfaces;

import seguranca.api.domain.Perfil.dtos.CadastroPerfilDto;
import seguranca.api.domain.Perfil.Perfil;

public interface IPerfilService {
    Perfil cadastrar(CadastroPerfilDto dto);
}
