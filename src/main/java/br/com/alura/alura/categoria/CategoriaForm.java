package br.com.alura.alura.categoria;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class CategoriaForm {

    @NotBlank @Length(min = 1, max = 50)
    private String titulo;
    @NotBlank @Length(min = 1, max = 50)
    private String cor;

    public CategoriaForm() {}

    public CategoriaForm(String titulo, String cor) {
        this.titulo = titulo;
        this.cor = cor;
    }

    public Categoria convert() {
        return new Categoria(this.titulo, this.cor);
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}
