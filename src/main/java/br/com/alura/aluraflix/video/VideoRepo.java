package br.com.alura.aluraflix.video;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepo extends JpaRepository<Video, Long> {

    List<Video> findAllByCategoria_Id(Long categoriaId);

    List<Video> findAllByTitulo(String titulo);

    List<Video> findAllByCategoria_Titulo(String categoriaTitulo);
}
