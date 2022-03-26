package br.com.alura.alura.video;

import br.com.alura.alura.categoria.Categoria;
import br.com.alura.alura.categoria.CategoriaRepo;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

public class VideoFormUpdate {

    private Long categoriaId;
    @NotBlank @Length(min = 1, max = 50)
    private String titulo;
    @NotBlank @Length(min = 5, max = 250)
    private String descricao;
    @NotBlank @URL
    private String url;

    public VideoFormUpdate() {}

    public VideoFormUpdate(Long categoriaId, String titulo, String descricao, String url) {
        this.categoriaId = categoriaId;
        this.titulo = titulo;
        this.descricao = descricao;
        this.url = url;
    }

    public Video update(Video video, CategoriaRepo categoriaRepo) {
        if(this.categoriaId != null) {
            Optional<Categoria> categoria = categoriaRepo.findById(this.categoriaId);
            categoria.ifPresent(video::setCategoria);
        }
        Optional<Categoria> categoriaDefault = categoriaRepo.findById(1L);
        video.setCategoria(categoriaDefault.get());
        video.setTitulo(this.titulo);
        video.setDescricao(this.descricao);
        video.setUrl(this.url);

        return video;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
