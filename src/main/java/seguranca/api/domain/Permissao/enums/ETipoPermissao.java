package seguranca.api.domain.Permissao.enums;

import lombok.Getter;

@Getter
public enum ETipoPermissao {
    ENDPOINT("ENDPOINT"),
    COMPONENT("COMPONENT");

    public String label;
    ETipoPermissao(String label) {
        this.label = label;
    }
}
