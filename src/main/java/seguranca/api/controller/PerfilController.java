package seguranca.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import seguranca.api.domain.Perfil.dtos.CadastroPerfilDto;
import seguranca.api.domain.Perfil.interfaces.IPerfilService;
import seguranca.api.infra.validation.interfaces.INotificadorService;

@RestController
@RequestMapping("perfil")
public class PerfilController {
    @Autowired
    IPerfilService perfilService;
    @Autowired
    INotificadorService notificador;

    @PostMapping("/cadastro")
    @Transactional
    public ResponseEntity cadastrar(CadastroPerfilDto dto) {
        var perfil = perfilService.cadastrar(dto);
        return notificador.retornoApi(perfil);
    }
}
