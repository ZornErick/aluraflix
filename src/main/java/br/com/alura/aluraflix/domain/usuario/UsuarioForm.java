package br.com.alura.aluraflix.domain.usuario;

import br.com.alura.aluraflix.util.crud.Form;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UsuarioForm implements Form<Usuario> {

    @NotBlank
    private String nome;
    @NotBlank
    private String email;
    @NotBlank
    private String senha;

    public Usuario convert() {
        return new Usuario(this.nome, this.email, new BCryptPasswordEncoder().encode(senha));
    }
}
