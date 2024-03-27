package seguranca.api.domain.Permissao.services;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import seguranca.api.domain.Perfil.Perfil;
import seguranca.api.domain.Permissao.Permissao;
import seguranca.api.domain.Permissao.dtos.CadastroPermissaoDto;
import seguranca.api.domain.Permissao.enums.ETipoPermissao;
import seguranca.api.domain.Permissao.interfaces.IPermissaoService;
import seguranca.api.infra.repository.IPerfilRepository;
import seguranca.api.infra.repository.IPermissaoRepository;
import seguranca.api.infra.validation.enums.ETipoNotificacao;
import seguranca.api.infra.validation.interfaces.INotificadorService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.context.annotation.ScopedProxyMode;

@Service
@Scope(value = WebApplicationContext.SCOPE_REQUEST,
        proxyMode = ScopedProxyMode.TARGET_CLASS)
public class PermissaoService implements IPermissaoService {
    @Autowired
    private IPermissaoRepository permissaoRepository;
    @Autowired
    private IPerfilRepository perfilRepository;
    @Autowired
    private INotificadorService notificador;

    public Permissao cadastrarPermissao(CadastroPermissaoDto dto) {
        var perfil = perfilRepository.findByNome(dto.nomePerfil());

        if (perfil == null)
            notificador.addNotificacao(ETipoNotificacao.ERRO_DE_DOMINIO.value, "Perfil não existe.");

        if (dto.identificador() == null)
            notificador.addNotificacao(ETipoNotificacao.ERRO_DE_DOMINIO.value, "Indentificador não informado.");

        if (dto.tipoPermissao() == null)
            notificador.addNotificacao(ETipoNotificacao.ERRO_DE_DOMINIO.value, "Tipo notificação não informado.");

        if (notificador.temNotificacaoDominio())
            return null;

        Permissao permissao = new Permissao(null, dto.identificador(), dto.tipoPermissao(), null, null, null);
        var perfis = new HashSet<Perfil>();
        perfis.add(perfil);
        permissao.setPerfis(perfis);
        if (dto.tipoPermissao() == ETipoPermissao.COMPONENT) {
            permissao = cadastrarPermissaoComponent(perfil, permissao, dto);
        } else if (dto.tipoPermissao() == ETipoPermissao.ENDPOINT) {
            permissao = cadastrarPermissaoEndpoint(perfil, permissao, dto);
        }

        permissaoRepository.save(permissao);

        return permissao;
    }

    private Permissao cadastrarPermissaoComponent(Perfil perfil, Permissao permissao, CadastroPermissaoDto dto) {
        if (dto.nomeComponente() == null)
            notificador.addNotificacao(ETipoNotificacao.ERRO_DE_DOMINIO.value, "Componente deve ser informado.");

        if (notificador.temNotificacaoDominio())
            return null;

        permissao.setNomeComponente(dto.nomeComponente());

        return permissao;
    }

    private Permissao cadastrarPermissaoEndpoint(Perfil perfil, Permissao permissao, CadastroPermissaoDto dto) {
        if (dto.endpoint() == null)
            notificador.addNotificacao(ETipoNotificacao.ERRO_DE_DOMINIO.value, "Endpoint deve ser informado.");

        if (notificador.temNotificacaoDominio())
            return null;

        permissao.setEndpoint(dto.endpoint());

        return permissao;
    }
}
