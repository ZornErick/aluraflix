package br.com.alura.aluraflix.categoria;

import br.com.alura.aluraflix.util.crud.Form;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CategoriaForm implements Form<Categoria> {

    @NotBlank
    private String titulo;
    @NotBlank
    private String cor;

    public Categoria convert() {
        return new Categoria(this.titulo, this.cor);
    }
}
