package seguranca.api.domain.Perfil.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import seguranca.api.domain.Perfil.dtos.CadastroPerfilDto;
import seguranca.api.domain.Perfil.interfaces.IValidacaoPerfil;
import seguranca.api.infra.repository.IPerfilRepository;
import seguranca.api.infra.validation.interfaces.INotificadorService;

@Service
@Scope(value = WebApplicationContext.SCOPE_REQUEST,
        proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ValidacaoPerfilJaExisteService implements IValidacaoPerfil {
    @Autowired
    private IPerfilRepository repository;
    @Autowired
    private INotificadorService notificador;

    @Override
    public void validar(CadastroPerfilDto dto) {
        var perfil = repository.findByNome(dto.nome());

        if (perfil != null)
            notificador.addNotificacao("Erro de domínio", "Perfil já cadastrado.");
    }
}
