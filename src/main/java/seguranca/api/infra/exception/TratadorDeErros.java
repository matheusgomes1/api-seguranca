package seguranca.api.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import seguranca.api.infra.validation.DadosErroValidacao;
import seguranca.api.infra.validation.RetornoApi;

import java.util.ArrayList;

@RestControllerAdvice
public class TratadorDeErros {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();

        var retornoApi = new RetornoApi<String>();
        retornoApi.setStatusHttp(400);
        retornoApi.setErros(erros.stream().map(err ->   "Campo <" + err.getField() + "> : " + err.getDefaultMessage()).toList());
        return ResponseEntity.badRequest().body(retornoApi);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity tratarErro400(HttpMessageNotReadableException ex) {

        var retornoApi = new RetornoApi<String>();
        retornoApi.setStatusHttp(400);

        var erro = new ArrayList<String>();
        erro.add(ex.getMessage());
        retornoApi.setErros(erro);

        return ResponseEntity.badRequest().body(retornoApi);
    }
}
