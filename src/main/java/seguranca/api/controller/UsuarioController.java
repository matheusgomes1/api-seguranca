package seguranca.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import seguranca.api.domain.Usuario.dtos.CadastroUsuarioDto;
import seguranca.api.domain.Usuario.dtos.LoginDto;
import seguranca.api.domain.Usuario.interfaces.IUsuarioService;
import seguranca.api.domain.UsuarioPerfil.dtos.AtribuirPerfilDto;
import seguranca.api.domain.UsuarioPerfil.interfaces.IUsuarioPerfilService;
import seguranca.api.infra.validation.interfaces.INotificadorService;

@RestController
@RequestMapping("usuario")
public class UsuarioController {
    @Autowired
    IUsuarioService usuarioService;
    @Autowired
    IUsuarioPerfilService usuarioPerfilService;
    @Autowired
    INotificadorService notificador;

    @PostMapping("/cadastro")
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid CadastroUsuarioDto cadastroUsuarioDto, UriComponentsBuilder uriBuilder) {

        var usuario = usuarioService.cadastrarUsuario(cadastroUsuarioDto);

        return notificador.retornoApi(usuario);
    }

    @PostMapping("atribuirPerfil")
    @Transactional
    public ResponseEntity atribuirPerfil(@Valid AtribuirPerfilDto dto) {
        usuarioPerfilService.atribuirPerfil(dto.login(), dto.nomePerfil());

        return notificador.retornoApi("perfil adicionado");
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginDto loginDto) {
        var result = usuarioService.login(loginDto);

        return notificador.retornoApi(result);
    }

    @GetMapping("/listar")
    public ResponseEntity listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var result = usuarioService.listar(paginacao);

        return notificador.retornoApi(result);
    }
}
