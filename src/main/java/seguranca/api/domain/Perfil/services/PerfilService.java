package seguranca.api.domain.Perfil.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import seguranca.api.domain.Perfil.Perfil;
import seguranca.api.domain.Perfil.dtos.CadastroPerfilDto;
import seguranca.api.domain.Perfil.interfaces.IValidacaoPerfil;
import seguranca.api.domain.Perfil.interfaces.IPerfilService;
import seguranca.api.infra.repository.IPerfilRepository;
import seguranca.api.infra.validation.interfaces.INotificadorService;

import java.util.List;

@Service
@Scope(value = WebApplicationContext.SCOPE_REQUEST,
        proxyMode = ScopedProxyMode.TARGET_CLASS)
public class PerfilService implements IPerfilService {
    @Autowired
    private IPerfilRepository repository;
    @Autowired
    private INotificadorService notificador;
    @Autowired
    List<IValidacaoPerfil> validacoesPerfil;

    public Perfil cadastrar(CadastroPerfilDto dto) {
        validar(dto);

        if (notificador.temNotificacaoDominio())
            return null;

        var perfil = new Perfil();
        perfil.setNome(dto.nome());
        perfil.setDescricao(dto.descricao());
        perfil.setAtivo(dto.ativo());
        repository.save(perfil);

        return perfil;
    }

    private void validar(CadastroPerfilDto dto) {
        for (IValidacaoPerfil validacao: validacoesPerfil) {
            validacao.validar(dto);
        }
    }
}
