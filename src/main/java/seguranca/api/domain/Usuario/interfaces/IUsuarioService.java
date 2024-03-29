package seguranca.api.domain.Usuario.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import seguranca.api.domain.Usuario.dtos.CadastroUsuarioDto;
import seguranca.api.domain.Usuario.dtos.ListagemUsuarioDto;
import seguranca.api.domain.Usuario.dtos.LoginDto;
import seguranca.api.domain.Usuario.Usuario;
import seguranca.api.infra.security.DadosTokenJWT;

public interface IUsuarioService {
    Usuario cadastrarUsuario(CadastroUsuarioDto cadastroUsuarioDto);
    DadosTokenJWT login(LoginDto loginDto);

    Page<ListagemUsuarioDto> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao);
}
