package seguranca.api.infra.validation;
import lombok.Getter;

@Getter
public class NotificacaoDominio {

     public NotificacaoDominio(String nome, String mensagem) {
         this.nome = nome == null ? mensagem : nome;
         this.mensagem = mensagem;
     }

    private String nome;
    private String mensagem;
}
