package br.com.alura.aluraflix.video;

import br.com.alura.aluraflix.util.crud.UpdateForm;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;

@Setter
public class VideoUpdateForm implements UpdateForm<Video> {

    @NotBlank
    private String titulo;
    @NotBlank
    private String descricao;
    @NotBlank @URL
    private String url;

    public Video update(Video entity) {
        entity.setTitulo(this.titulo);
        entity.setDescricao(this.descricao);
        entity.setUrl(this.url);

        return entity;
    }
}
