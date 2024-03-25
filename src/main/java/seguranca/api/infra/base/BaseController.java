package seguranca.api.infra.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import seguranca.api.infra.validation.RetornoApi;
import seguranca.api.infra.validation.interfaces.INotificadorService;

public abstract class BaseController {
    @Autowired
    private INotificadorService _notificador;

    public <T extends Object> ResponseEntity retornoApi(T dados) {
        if (_notificador.semNotificacaoDominio()) {
            var retornoApi = new RetornoApi<T>();
            retornoApi.dados = dados;
            retornoApi.statusHttp = 200;

            return ResponseEntity.ok(retornoApi);
        } else {
            var retornoApi = new RetornoApi<T>();
            retornoApi.statusHttp = 400;
            retornoApi.erros = _notificador.getNotificacoesDominio().stream().map(val ->   val.getNome() + " - " + val.getMensagem()).toList();
            return ResponseEntity.badRequest().body(retornoApi);
        }
    }
}
