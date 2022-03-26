package br.com.alura.alura.video;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class VideoDto {

    private Long id;
    private Long categoriaId;
    private String titulo;
    private String descricao;
    private String url;
    
    public VideoDto() {}

    public VideoDto(Video video) {
        this.id = video.getId();
        this.categoriaId = video.getCategoria().getId();
        this.titulo = video.getTitulo();
        this.descricao = video.getDescricao();
        this.url = video.getUrl();
    }

    public List<VideoDto> convert(List<Video> videos) {
        return videos.stream().map(VideoDto::new).collect(Collectors.toList());
    }

    public Page<VideoDto> convert(Page<Video> videos) {
        return videos.map(VideoDto::new);
    }

    public Long getId() {
        return id;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getUrl() {
        return url;
    }
}
