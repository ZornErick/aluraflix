package br.com.alura.aluraflix.domain.video;

import br.com.alura.aluraflix.domain.video.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/videos")
public class VideoController {

    private final VideoService videoService;

    @Autowired
    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping
    public ResponseEntity<List<VideoDto>> findAllVideos(String search) {
        return ResponseEntity.ok(new VideoDto().convert(videoService.findVideosByTitulo(search)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideoDto> findVideoById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(new VideoDto(videoService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<VideoDto> createVideo(@RequestBody @Valid VideoForm videoForm, UriComponentsBuilder uriBuilder) {
        Video video = videoService.create(videoForm);
        URI uri = uriBuilder.path("/videos/{id}").buildAndExpand(video.getId()).toUri();
        return ResponseEntity.created(uri).body(new VideoDto(video));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VideoDto> updateVideo(@PathVariable("id") Long id, @RequestBody @Valid VideoUpdateForm videoUpdateForm) {
        Video video = videoService.update(id, videoUpdateForm);
        return ResponseEntity.ok(new VideoDto(video));
    }

    @DeleteMapping("/{id}")
    public void deleteVideoById(@PathVariable("id") Long id) {
        videoService.delete(id);
    }
}
