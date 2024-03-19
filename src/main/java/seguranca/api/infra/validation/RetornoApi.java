package seguranca.api.infra.validation;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RetornoApi<T> {
    int statusHttp;
    List<String> erros;
    T dados;
}
