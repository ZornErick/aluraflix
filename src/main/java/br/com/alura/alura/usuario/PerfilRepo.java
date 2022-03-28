package br.com.alura.alura.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilRepo extends JpaRepository<Perfil, Long> {
}
