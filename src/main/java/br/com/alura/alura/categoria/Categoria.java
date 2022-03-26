package br.com.alura.alura.categoria;

import javax.persistence.*;

@Entity
@Table(name = "categorias")
public class Categoria {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String cor;

    public Categoria() {}

    public Categoria(String titulo, String cor) {
        this.titulo = titulo;
        this.cor = cor;
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

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}
