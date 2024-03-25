package seguranca.api.infra.validation;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import seguranca.api.infra.validation.interfaces.INotificadorService;

import java.util.ArrayList;
import java.util.List;

@Service
@Scope(value = WebApplicationContext.SCOPE_REQUEST,
        proxyMode = ScopedProxyMode.TARGET_CLASS)
public class NotificadorService implements INotificadorService {
    private List<NotificacaoDominio> notificacoesDominio = new ArrayList<NotificacaoDominio>();

    public boolean semNotificacaoDominio() {
        return notificacoesDominio.isEmpty();
    }

    public boolean temNotificacaoDominio() {
        return !notificacoesDominio.isEmpty();
    }

    public void addNotificacao(String nome, String mensagem) {
        notificacoesDominio.add(new NotificacaoDominio(nome, mensagem));
    }

    public List<NotificacaoDominio> getNotificacoesDominio() {
        return notificacoesDominio;
    }
}
