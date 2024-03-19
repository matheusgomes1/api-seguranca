package seguranca.api.domain.Usuario.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import seguranca.api.domain.Usuario.dtos.CadastroUsuarioDto;
import seguranca.api.domain.Usuario.interfaces.IValidacaoUsuario;
import seguranca.api.infra.repository.IUsuarioRepository;
import seguranca.api.infra.validation.interfaces.INotificadorService;

@Service
@Scope(value = WebApplicationContext.SCOPE_REQUEST,
        proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ValidarEmailJaExisteService implements IValidacaoUsuario {
    @Autowired
    INotificadorService notificador;
    @Autowired
    IUsuarioRepository repository;

    public void Validar(CadastroUsuarioDto dto) {
        var usuario = repository.findByemail(dto.email());

        if (usuario != null)
            notificador.addNotificacao("Erro de domínio","Email já cadastrado.");
    }
}
