package br.com.alura.aluraflix.domain.usuario.service;

import br.com.alura.aluraflix.domain.usuario.Usuario;
import br.com.alura.aluraflix.domain.usuario.UsuarioForm;
import br.com.alura.aluraflix.domain.usuario.UsuarioRepo;
import br.com.alura.aluraflix.util.crud.Form;
import br.com.alura.aluraflix.util.crud.UpdateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepo usuarioRepo;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepo usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    @Override
    public List<Usuario> findAll() {
        return null;
    }

    @Override
    public Usuario findById(Long id) {
        return null;
    }

    @Override
    public Usuario create(Form<Usuario> form) {
        UsuarioForm usuarioForm = (UsuarioForm) form;
        return usuarioRepo.save(usuarioForm.convert());
    }

    @Override
    public Usuario update(Long id, UpdateForm<Usuario> updateForm) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepo.findByEmail(username);
        if(usuario.isEmpty()) throw new UsernameNotFoundException("Usuário não encontrado!");
        return usuario.get();
    }
}
