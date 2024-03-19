package seguranca.api.domain.Perfil;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import seguranca.api.domain.Permissao.Permissao;
import seguranca.api.domain.Usuario.Usuario;

import java.util.Set;

@Table(name = "perfil")
@Entity(name="Perfil")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer perfilId;
    String nome;
    String descricao;
    boolean ativo;
    @ManyToMany
    @JoinTable(name="usuario_perfil",
               joinColumns = @JoinColumn(name="perfilId"),
               inverseJoinColumns = @JoinColumn(name = "usuarioId"))
    Set<Usuario> usuarios;

    @ManyToMany(mappedBy = "perfis")
    Set<Permissao> permissoes;
}
