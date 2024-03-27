package seguranca.api.domain.Permissao.dtos;

import seguranca.api.domain.Permissao.enums.ETipoPermissao;

public record CadastroPermissaoDto(String identificador, ETipoPermissao tipoPermissao, String endpoint, String nomeComponente, String nomePerfil) {
}
