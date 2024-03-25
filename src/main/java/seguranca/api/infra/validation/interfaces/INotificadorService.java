package seguranca.api.infra.validation.interfaces;

import seguranca.api.infra.validation.NotificacaoDominio;

import java.util.List;

public interface INotificadorService {
    boolean semNotificacaoDominio();
    boolean temNotificacaoDominio();
    void addNotificacao(String nome, String mensagem);
    List<NotificacaoDominio> getNotificacoesDominio();
}
