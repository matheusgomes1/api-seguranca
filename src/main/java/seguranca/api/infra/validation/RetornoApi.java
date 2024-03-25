package seguranca.api.infra.validation;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RetornoApi<T> {
    public int statusHttp;
    public List<String> erros;
    public T dados;
}
