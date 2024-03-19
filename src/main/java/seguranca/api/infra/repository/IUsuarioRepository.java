package seguranca.api.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import seguranca.api.domain.Usuario.Usuario;

public interface IUsuarioRepository  extends JpaRepository<Usuario, Long> {
    Usuario findBylogin(String login);

    Usuario findByemail(String email);
}
