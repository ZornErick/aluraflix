package br.com.alura.aluraflix.security.token;

import br.com.alura.aluraflix.domain.usuario.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Profile("dev")
@Service
public class TokenService {

    @Value("${aluraflix.jwt.secret}")
    private String secret;
    @Value("${aluraflix.jwt.expiration}")
    private String expiration;

    public String token(Authentication authentication) {
        Usuario usuario = (Usuario) authentication.getPrincipal();
        Date date = new Date();
        Date dateExpiration = new Date(date.getTime() + Long.parseLong(expiration));

        return Jwts.builder()
                .setIssuer("API aluraflix")
                .setSubject(usuario.getId().toString())
                .setIssuedAt(dateExpiration)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public Boolean isTokenValid(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    public Long getId(String token) {
        Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return Long.parseLong(body.getSubject());
    }
}
