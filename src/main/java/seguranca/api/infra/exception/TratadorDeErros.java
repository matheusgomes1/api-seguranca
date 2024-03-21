package seguranca.api.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
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

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity tratarErroBadCredentials(BadCredentialsException ex) {
        var retornoApi = new RetornoApi<String>();
        retornoApi.setStatusHttp(HttpStatus.UNAUTHORIZED.value());

        var erro = new ArrayList<String>();
        erro.add(ex.getMessage());
        retornoApi.setErros(erro);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(retornoApi);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity tratarErroAuthentication() {
        var retornoApi = new RetornoApi<String>();
        retornoApi.setStatusHttp(HttpStatus.UNAUTHORIZED.value());

        var erro = new ArrayList<String>();
        erro.add("Falha na autenticação");
        retornoApi.setErros(erro);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(retornoApi);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity tratarErroAcessoNegado() {
        var retornoApi = new RetornoApi<String>();
        retornoApi.setStatusHttp(HttpStatus.FORBIDDEN.value());

        var erro = new ArrayList<String>();
        erro.add("Acesso negado");
        retornoApi.setErros(erro);

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(retornoApi);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity tratarErro500(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " +ex.getLocalizedMessage());
    }
}
