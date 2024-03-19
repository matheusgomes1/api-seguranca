package seguranca.api.domain.Permissao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import seguranca.api.domain.Perfil.Perfil;
import seguranca.api.domain.Permissao.enums.TipoPermissaoEnum;

import java.util.Set;

@Table(name = "permissao")
@Entity(name = "Permissao")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Permissao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer permissaoId;
    @Enumerated(EnumType.STRING)
    TipoPermissaoEnum tipoPermissao;
    String endpoint;
    String expressaoRegular;
    String nomeComponente;

    @ManyToMany
    @JoinTable(name="perfil_permissao",
            joinColumns = @JoinColumn(name="permissaoId"),
            inverseJoinColumns = @JoinColumn(name = "perfilId"))
    Set<Perfil> perfis;
}
