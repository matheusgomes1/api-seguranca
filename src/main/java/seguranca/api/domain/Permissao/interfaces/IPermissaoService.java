package seguranca.api.domain.Permissao.interfaces;

import seguranca.api.domain.Permissao.Permissao;
import seguranca.api.domain.Permissao.dtos.CadastroPermissaoDto;

public interface IPermissaoService {
    Permissao cadastrarPermissao(CadastroPermissaoDto dto);
}
