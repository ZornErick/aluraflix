package br.com.alura.alura.categoria;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class CategoriaDto {

    private Long id;
    private String titulo;
    private String cor;

    public CategoriaDto() {}

    public CategoriaDto(Categoria categoria) {
        this.id = categoria.getId();
        this.titulo = categoria.getTitulo();
        this.cor = categoria.getCor();
    }

    public List<CategoriaDto> convert(List<Categoria> categorias) {
        return categorias.stream().map(CategoriaDto::new).collect(Collectors.toList());
    }

    public Page<CategoriaDto> convert(Page<Categoria> categorias) {
        return categorias.map(CategoriaDto::new);
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getCor() {
        return cor;
    }
}
