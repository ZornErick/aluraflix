package br.com.alura.aluraflix.security.auth;

import br.com.alura.aluraflix.security.login.LoginForm;
import br.com.alura.aluraflix.security.token.TokenDto;
import br.com.alura.aluraflix.security.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Profile("dev")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<TokenDto> auth(@RequestBody LoginForm loginForm) {
        UsernamePasswordAuthenticationToken login = loginForm.convert();

        try {
            Authentication authentication = authenticationManager.authenticate(login);
            String token = tokenService.token(authentication);
            return ResponseEntity.ok(new TokenDto(token, "Bearer"));
        } catch(Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
