package br.com.alura.aluraflix.security.token;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Profile;

@Profile("dev")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TokenDto {

    private String token;
    private String type;
}
