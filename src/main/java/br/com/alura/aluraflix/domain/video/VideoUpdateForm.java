package br.com.alura.aluraflix.domain.video;

import br.com.alura.aluraflix.domain.categoria.Categoria;
import br.com.alura.aluraflix.util.crud.UpdateForm;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class VideoUpdateForm implements UpdateForm<Video> {

    @NotBlank
    private String titulo;
    @NotBlank
    private String descricao;
    @NotBlank @URL
    private String url;
    private Long categoriaId = 1L;

    public Video update(Video entity, Categoria categoria) {
        entity.setTitulo(this.titulo);
        entity.setDescricao(this.descricao);
        entity.setUrl(this.url);
        entity.setCategoria(categoria);

        return entity;
    }
}
