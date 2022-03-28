package br.com.alura.alura.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepo usuarioRepo;

    @GetMapping("/{id}")
    public ResponseEntity<?> readById(@PathVariable("id") Long id) {
        Optional<Usuario> usuario = usuarioRepo.findById(id);
        if(usuario.isPresent()) {
            return ResponseEntity.ok(new UsuarioDto(usuario.get()));
        }
        return new ResponseEntity<>("Id não encontrado!", HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<UsuarioDto> create(@RequestBody @Valid UsuarioForm usuarioForm, UriComponentsBuilder uriBuilder) {
        Usuario usuario = usuarioForm.convert();
        usuarioRepo.save(usuario);

        URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new UsuarioDto(usuario));
    }
}
