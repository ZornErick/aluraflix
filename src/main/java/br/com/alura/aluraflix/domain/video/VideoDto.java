package br.com.alura.aluraflix.domain.video;

import br.com.alura.aluraflix.domain.categoria.Categoria;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class VideoDto {

    private Long id;
    private String titulo;
    private String descricao;
    private String url;

    private Categoria categoria;

    public VideoDto(Video video) {
        this.id = video.getId();
        this.titulo = video.getTitulo();
        this.descricao = video.getDescricao();
        this.url = video.getUrl();
        this.categoria = video.getCategoria();
    }

    public List<VideoDto> convert(List<Video> videos) {
        return videos.stream().map(VideoDto::new).collect(Collectors.toList());
    }
}
