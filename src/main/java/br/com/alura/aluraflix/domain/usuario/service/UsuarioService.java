package br.com.alura.aluraflix.domain.usuario.service;

import br.com.alura.aluraflix.domain.usuario.Usuario;
import br.com.alura.aluraflix.util.crud.CrudService;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsuarioService extends CrudService<Usuario>, UserDetailsService {
}
