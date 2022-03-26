package br.com.alura.alura.video;

import br.com.alura.alura.categoria.Categoria;

import javax.persistence.*;

@Entity
@Table(name = "videos")
public class Video {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    private Categoria categoria;

    public Video() {}

    public Video(String titulo, String descricao, String url, Categoria categoria) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.url = url;
        this.categoria = categoria;
    }

    public Video(String titulo, String descricao, String url) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
