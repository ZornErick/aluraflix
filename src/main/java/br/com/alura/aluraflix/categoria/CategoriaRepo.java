package br.com.alura.aluraflix.categoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoriaRepo extends JpaRepository<Categoria, Long> {
}
