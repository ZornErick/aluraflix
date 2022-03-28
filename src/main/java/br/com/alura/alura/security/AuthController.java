package br.com.alura.alura.security;

import br.com.alura.alura.security.token.TokenDto;
import br.com.alura.alura.security.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> auth(@RequestBody @Valid LoginForm loginForm) {
        UsernamePasswordAuthenticationToken login = loginForm.convert();

        try {
            Authentication authentication = authManager.authenticate(login);
            String token = tokenService.token(authentication);
            return ResponseEntity.ok(new TokenDto(token, "Bearer"));
        } catch(AuthenticationException e) {
            return new ResponseEntity<>("Usuário ou senha inválido", HttpStatus.FORBIDDEN);
        }
    }
}
