package seguranca.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import seguranca.api.domain.Perfil.dtos.CadastroPerfilDto;
import seguranca.api.domain.Perfil.interfaces.IPerfilService;
import seguranca.api.infra.base.BaseController;
import seguranca.api.infra.validation.interfaces.INotificadorService;

@RestController
@RequestMapping("perfil")
public class PerfilController extends BaseController {
    @Autowired
    IPerfilService perfilService;

    @PostMapping("/cadastro")
    @Transactional
    public ResponseEntity cadastrar(@RequestBody CadastroPerfilDto dto) {
        var perfil = perfilService.cadastrar(dto);
        return retornoApi(perfil);
    }
}
