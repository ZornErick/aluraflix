package br.com.alura.alura.video;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoRepo extends JpaRepository<Video, Long> {
    List<Video> findByCategoria_Id(Long id);
    Page<Video> findByTituloContaining(String titulo, Pageable pageable);
}
