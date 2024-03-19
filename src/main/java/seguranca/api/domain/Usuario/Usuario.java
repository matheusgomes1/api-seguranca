package seguranca.api.domain.Usuario;

import jakarta.persistence.*;
import lombok.*;
import seguranca.api.domain.Perfil.Perfil;

import java.util.Set;

@Table(name="usuario")
@Entity(name="Usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "usuarioId")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long usuarioId;
    String nome;
    String email;
    String login;
    String senha;
    boolean ativo;
    @ManyToMany(mappedBy = "usuarios")
    Set<Perfil> perfis;
}
