package br.com.alura.aluraflix.video;

import br.com.alura.aluraflix.util.crud.Form;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;

@Setter
public class VideoForm implements Form<Video> {

    @NotBlank
    private String titulo;
    @NotBlank
    private String descricao;
    @NotBlank @URL
    private String url;

    public Video convert() {
        return new Video(this.titulo, this.descricao, this.url);
    }
}
