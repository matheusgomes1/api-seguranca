package seguranca.api.domain.Permissao.enums;

import lombok.Getter;

@Getter
public enum TipoPermissaoEnum {
    CONTROLLER("CONTROLLER"),
    COMPONENT("COMPONENT");

    public String label;
    TipoPermissaoEnum(String label) {
        this.label = label;
    }
}
