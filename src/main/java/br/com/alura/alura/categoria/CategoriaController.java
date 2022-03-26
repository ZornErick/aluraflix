package br.com.alura.alura.categoria;

import br.com.alura.alura.video.Video;
import br.com.alura.alura.video.VideoDto;
import br.com.alura.alura.video.VideoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepo categoriaRepo;

    @Autowired
    private VideoRepo videoRepo;

    @GetMapping
    public Page<CategoriaDto> readAll(@PageableDefault(size = 5) Pageable pageable) {
        Page<Categoria> categorias = categoriaRepo.findAll(pageable);
        return new CategoriaDto().convert(categorias);
    }

    @GetMapping("/{id}/videos")
    public ResponseEntity<?> readVideosByCategoria(@PathVariable("id") Long id) {
        List<Video> videos = videoRepo.findByCategoria_Id(id);
        if(!videos.isEmpty()) {
            return ResponseEntity.ok(new VideoDto().convert(videos));
        }
        return new ResponseEntity<>("Nenhum vídeo registrado nessa categoria!", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readById(@PathVariable("id") Long id) {
        Optional<Categoria> categoria = categoriaRepo.findById(id);
        if(categoria.isPresent()) {
            return ResponseEntity.ok(new CategoriaDto(categoria.get()));
        }
        return new ResponseEntity<>("Id não encontrado!", HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CategoriaDto> create(@RequestBody @Valid CategoriaForm categoriaForm, UriComponentsBuilder uriBuilder) {
        Categoria categoria = categoriaForm.convert();
        categoriaRepo.save(categoria);

        URI uri = uriBuilder.path("/categorias/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).body(new CategoriaDto(categoria));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<CategoriaDto> update(@PathVariable("id") Long id, @RequestBody @Valid CategoriaFormUpdate categoriaFormUpdate) {
        Optional<Categoria> categoria = categoriaRepo.findById(id);
        if(categoria.isPresent()) {
            Categoria categoriaUpdate = categoriaFormUpdate.update(categoria.get());
            return ResponseEntity.ok(new CategoriaDto(categoriaUpdate));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        Optional<Categoria> categoria = categoriaRepo.findById(id);
        if(categoria.isPresent()) {
            categoriaRepo.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
