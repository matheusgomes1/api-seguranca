package seguranca.api.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import seguranca.api.domain.Perfil.Perfil;

public interface IPerfilRepository extends JpaRepository<Perfil, Integer> {
    Perfil findByNome(String nome);
}
