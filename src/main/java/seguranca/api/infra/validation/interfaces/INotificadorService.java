package seguranca.api.infra.validation.interfaces;

import org.springframework.http.ResponseEntity;

public interface INotificadorService {
    boolean semNotificacaoDominio();
    boolean temNotificacaoDominio();
    void addNotificacao(String nome, String mensagem);
    <T extends Object> ResponseEntity retornoApi(T dados);
}
