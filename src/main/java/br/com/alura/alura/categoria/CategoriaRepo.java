package br.com.alura.alura.categoria;

import br.com.alura.alura.video.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepo extends JpaRepository<Categoria, Long> {
}
