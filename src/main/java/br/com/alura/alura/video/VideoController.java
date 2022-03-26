package br.com.alura.alura.video;

import br.com.alura.alura.categoria.CategoriaRepo;
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
@RequestMapping("/videos")
public class VideoController {

    @Autowired
    private VideoRepo videoRepo;

    @Autowired
    private CategoriaRepo categoriaRepo;

    @GetMapping
    public Page<VideoDto> readAll(@RequestParam(required = false) String search, @PageableDefault(size = 5) Pageable pageable) {
        Page<Video> videos;
        if(search == null) {
            videos = videoRepo.findAll(pageable);
        } else {
            videos = videoRepo.findByTituloContaining(search, pageable);
        }
        return new VideoDto().convert(videos);
    }

    @GetMapping("/free")
    public List<VideoDto> readFree() {
        List<Video> videos = videoRepo.findAll();
        if(videos.size() >= 10) {
            return new VideoDto().convert(videos.subList(0, 10));
        }
        return new VideoDto().convert(videos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readById(@PathVariable("id") Long id) {
        Optional<Video> video = videoRepo.findById(id);
        if(video.isPresent()) {
            return ResponseEntity.ok(new VideoDto(video.get()));
        }
        return new ResponseEntity<>("Id não encontrado!", HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<VideoDto> create(@RequestBody @Valid VideoForm videoForm, UriComponentsBuilder uriBuilder) {
        Video video = videoForm.convert(categoriaRepo);
        videoRepo.save(video);

        URI uri = uriBuilder.path("/videos/{id}").buildAndExpand(video.getId()).toUri();
        return ResponseEntity.created(uri).body(new VideoDto(video));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<VideoDto> update(@PathVariable("id") Long id, @RequestBody @Valid VideoFormUpdate videoFormUpdate) {
        Optional<Video> video = videoRepo.findById(id);
        if(video.isPresent()) {
            Video videoUpdate = videoFormUpdate.update(video.get(), categoriaRepo);
            return ResponseEntity.ok(new VideoDto(videoUpdate));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        Optional<Video> video = videoRepo.findById(id);
        if(video.isPresent()) {
            videoRepo.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
