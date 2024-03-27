package seguranca.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import seguranca.api.domain.Perfil.dtos.CadastroPerfilDto;
import seguranca.api.domain.Permissao.dtos.CadastroPermissaoDto;
import seguranca.api.domain.Permissao.services.PermissaoService;
import seguranca.api.infra.base.BaseController;

@RestController
@RequestMapping("permissao")
public class PermissaoController extends BaseController {
    @Autowired
    private PermissaoService permissaoService;


    @PostMapping("/cadastro")
    @Transactional
    public ResponseEntity cadastrar(@RequestBody CadastroPermissaoDto dto) {
        var permissao = permissaoService.cadastrarPermissao(dto);
        return retornoApi("permissão " + permissao.getIdentificador() + " adicionada.");
    }
}
