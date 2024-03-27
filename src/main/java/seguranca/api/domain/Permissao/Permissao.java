package seguranca.api.domain.Permissao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import seguranca.api.domain.Perfil.Perfil;
import seguranca.api.domain.Permissao.enums.ETipoPermissao;

import java.util.Set;

@Table(name = "permissao")
@Entity(name = "Permissao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Permissao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer permissaoId;
    String identificador;
    @Enumerated(EnumType.STRING)
    ETipoPermissao tipoPermissao;
    String endpoint;
    String nomeComponente;

    @ManyToMany
    @JoinTable(name="perfil_permissao",
            joinColumns = @JoinColumn(name="permissaoId"),
            inverseJoinColumns = @JoinColumn(name = "perfilId"))
    Set<Perfil> perfis;
}
