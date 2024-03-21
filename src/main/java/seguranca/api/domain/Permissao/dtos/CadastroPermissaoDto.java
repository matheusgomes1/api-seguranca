package seguranca.api.domain.Permissao.dtos;

import seguranca.api.domain.Permissao.enums.TipoPermissaoEnum;

public record CadastroPermissaoDto(String identificador, TipoPermissaoEnum tipoPermissao, String endpoint, String expressaoRegular, String nomeComponente, String nomePerfil) {
}
