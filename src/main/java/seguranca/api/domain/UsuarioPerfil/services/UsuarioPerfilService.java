package seguranca.api.domain.UsuarioPerfil.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import seguranca.api.domain.UsuarioPerfil.interfaces.IUsuarioPerfilService;
import seguranca.api.infra.repository.IPerfilRepository;
import seguranca.api.infra.repository.IUsuarioRepository;
import seguranca.api.infra.validation.enums.ETipoNotificacao;
import seguranca.api.infra.validation.interfaces.INotificadorService;

@Service
@Scope(value = WebApplicationContext.SCOPE_REQUEST,
        proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class UsuarioPerfilService implements IUsuarioPerfilService {
    @Autowired
    IUsuarioRepository usuarioRepository;
    @Autowired
    IPerfilRepository perfilRepository;
    @Autowired
    INotificadorService notificador;
    @Override
    public void atribuirPerfil(String login, String nomePerfil) {
        var usuario = usuarioRepository.findBylogin(login);

        if (usuario == null) {
            notificador.addNotificacao(ETipoNotificacao.ERRO_DE_DOMINIO.value, "Login não existe.");
            return;
        }

        var perfil = perfilRepository.findByNome(nomePerfil);

        if (perfil == null) {
            notificador.addNotificacao(ETipoNotificacao.ERRO_DE_DOMINIO.value, "Perfil não existe.");
            return;
        }
        var perfilJaCadastrado = !usuario.getPerfis().stream().filter(p -> p.getPerfilId() == perfil.getPerfilId()).toList().isEmpty();

        if (perfilJaCadastrado) {
            notificador.addNotificacao(ETipoNotificacao.ERRO_DE_DOMINIO.value, "Perfil " + perfil.getNome() + " já cadastrado para o login: " + login);
            return;
        }

        perfil.getUsuarios().add(usuario);
        usuario.getPerfis().add(perfil);

        usuarioRepository.save(usuario);
    }
}
