package seguranca.api.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import seguranca.api.domain.Permissao.Permissao;

public interface IPermissaoRepository extends JpaRepository<Permissao, Integer> {
    Permissao findByIdentificador(String identificador);
}
