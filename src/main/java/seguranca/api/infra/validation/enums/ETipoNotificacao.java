package seguranca.api.infra.validation.enums;

import lombok.Getter;

@Getter
public enum ETipoNotificacao {
    ERRO_DE_DOMINIO("Erro de domínio");

    public String value;

    ETipoNotificacao(String valor) {
        value = valor;
    }
}
