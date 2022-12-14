package br.com.alura.aluraflix.security.login;

import br.com.alura.aluraflix.domain.usuario.Usuario;
import br.com.alura.aluraflix.util.crud.Form;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Profile("dev")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoginForm implements Form<Usuario> {

    private String email;
    private String senha;

    public UsernamePasswordAuthenticationToken convert() {
        return new UsernamePasswordAuthenticationToken(this.email, this.senha);
    }
}
