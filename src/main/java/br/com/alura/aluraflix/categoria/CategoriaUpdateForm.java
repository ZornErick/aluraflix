package br.com.alura.aluraflix.categoria;

import br.com.alura.aluraflix.util.crud.UpdateForm;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CategoriaUpdateForm implements UpdateForm<Categoria> {

    @NotBlank
    private String titulo;
    @NotBlank
    private String cor;

    public Categoria update(Categoria entity) {
        entity.setTitulo(this.titulo);
        entity.setCor(this.cor);

        return entity;
    }
}
