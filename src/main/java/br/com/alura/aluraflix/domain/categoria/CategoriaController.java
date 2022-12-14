package br.com.alura.aluraflix.domain.categoria;

import br.com.alura.aluraflix.domain.categoria.service.CategoriaService;
import br.com.alura.aluraflix.domain.video.VideoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    @Autowired
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDto>> findAllCategorias() {
        return ResponseEntity.ok(new CategoriaDto().convert(categoriaService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDto> findCategoriaById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(new CategoriaDto(categoriaService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<CategoriaDto> createCategoria(@RequestBody @Valid CategoriaForm categoriaForm, UriComponentsBuilder uriBuilder) {
        Categoria categoria = categoriaService.create(categoriaForm);
        URI uri = uriBuilder.path("/categorias/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).body(new CategoriaDto(categoria));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDto> updateCategoria(@PathVariable("id") Long id, @RequestBody @Valid CategoriaUpdateForm categoriaUpdateForm) {
        Categoria categoria = categoriaService.update(id, categoriaUpdateForm);
        return ResponseEntity.ok(new CategoriaDto(categoria));
    }

    @DeleteMapping("/{id}")
    public void deleteCategoriaById(@PathVariable("id") Long id) {
        categoriaService.delete(id);
    }

    @GetMapping("/{id}/videos")
    public ResponseEntity<List<VideoDto>> findVideosByCategoria(@PathVariable("id") Long categoriaId) {
        return ResponseEntity.ok(new VideoDto().convert(categoriaService.findVideosByCategoriaId(categoriaId)));
    }
}
