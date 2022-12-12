package br.com.alura.aluraflix.video;

import br.com.alura.aluraflix.categoria.Categoria;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tb_video")
@NoArgsConstructor
@Getter
@Setter
public class Video {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private String titulo;
    @Column(length = 500)
    private String descricao;
    @Column(length = 500)
    private String url;

    @ManyToOne
    private Categoria categoria;

    public Video(String titulo, String descricao, String url, Categoria categoria) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.url = url;
        this.categoria = categoria;
    }
}
