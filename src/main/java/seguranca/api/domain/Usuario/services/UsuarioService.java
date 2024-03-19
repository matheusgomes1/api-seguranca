package seguranca.api.domain.Usuario.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import seguranca.api.domain.Usuario.Usuario;
import seguranca.api.domain.Usuario.dtos.CadastroUsuarioDto;
import seguranca.api.domain.Usuario.dtos.ListagemUsuarioDto;
import seguranca.api.domain.Usuario.interfaces.IUsuarioService;
import seguranca.api.domain.Usuario.dtos.LoginDto;
import seguranca.api.domain.Usuario.interfaces.IValidacaoUsuario;
import seguranca.api.infra.repository.IUsuarioRepository;
import seguranca.api.infra.validation.interfaces.INotificadorService;

import java.util.List;

@Service
@Scope(value = WebApplicationContext.SCOPE_REQUEST,
        proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UsuarioService implements IUsuarioService {
    @Autowired
    INotificadorService notificador;
    @Autowired
    IUsuarioRepository repository;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    List<IValidacaoUsuario> validacoesCadastroUsuario;
    @Autowired
    private ModelMapper modelMapper;

    private CadastroUsuarioDto _cadastroUsuarioDto;

    public Usuario cadastrarUsuario(CadastroUsuarioDto cadastroUsuarioDto) {
        _cadastroUsuarioDto = cadastroUsuarioDto;
        Validar();
        var usuario = new Usuario(null, cadastroUsuarioDto.nome(), cadastroUsuarioDto.email(), cadastroUsuarioDto.login(), passwordEncoder.encode(cadastroUsuarioDto.senha()), true, null);

        if (notificador.semNotificacaoDominio())
        {
            repository.save(usuario);

            return usuario;
        } else {
            return null;
        }
    }

    public boolean login(LoginDto loginDto) {
        var usuario = repository.findBylogin(loginDto.login());

        if (usuario == null) {
            notificador.addNotificacao("Erro de domínio", "Cadastro não encontrado");
            return false;
        }

        if (!usuario.isAtivo()) {
            notificador.addNotificacao("Erro de domínio", "Usuário inativo");
            return false;
        }

        if (passwordEncoder.matches(loginDto.senha(), usuario.getSenha())) {
            return true;
        }

        return false;
    }

    public Page<ListagemUsuarioDto> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var usuarioPage = repository.findAll(paginacao);

        List<ListagemUsuarioDto> dtos = usuarioPage.getContent()
                .stream()
                .map(usuario -> modelMapper.map(usuario, ListagemUsuarioDto.class))
                .toList();

        return new PageImpl<>(dtos, usuarioPage.getPageable(), usuarioPage.getTotalElements());
    }

    private void Validar() {
        for (var validacao: validacoesCadastroUsuario) {
            validacao.Validar(_cadastroUsuarioDto);
        }
    }
}
