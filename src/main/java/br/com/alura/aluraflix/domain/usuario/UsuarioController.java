package br.com.alura.aluraflix.domain.usuario;

import br.com.alura.aluraflix.domain.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioDto> createUsuario(@RequestBody @Valid UsuarioForm usuarioForm) {
        Usuario usuario = usuarioService.create(usuarioForm);
        return ResponseEntity.ok(new UsuarioDto(usuario));
    }
}
