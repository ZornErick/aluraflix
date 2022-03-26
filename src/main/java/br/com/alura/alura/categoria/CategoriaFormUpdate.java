package br.com.alura.alura.categoria;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class CategoriaFormUpdate {

    @NotBlank @Length(min = 1, max = 50)
    private String titulo;
    @NotBlank @Length(min = 1, max = 50)
    private String cor;

    public CategoriaFormUpdate() {}

    public CategoriaFormUpdate(String titulo, String cor) {
        this.titulo = titulo;
        this.cor = cor;
    }

    public Categoria update(Categoria categoria) {
        categoria.setTitulo(this.titulo);
        categoria.setCor(this.cor);

        return categoria;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}
