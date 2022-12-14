package br.com.alura.aluraflix.domain.categoria;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tb_categoria")
@NoArgsConstructor
@Getter
@Setter
public class Categoria {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String cor;

    public Categoria(String titulo, String cor) {
        this.titulo = titulo;
        this.cor = cor;
    }
}
