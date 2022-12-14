package br.com.alura.aluraflix.domain.video;

import br.com.alura.aluraflix.domain.categoria.Categoria;
import br.com.alura.aluraflix.util.crud.Form;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class VideoForm implements Form<Video> {

    @NotBlank
    private String titulo;
    @NotBlank
    private String descricao;
    @NotBlank @URL
    private String url;
    private Long categoriaId = 1L;

    public Video convert(Categoria categoria) {
        return new Video(this.titulo, this.descricao, this.url, categoria);
    }
}
