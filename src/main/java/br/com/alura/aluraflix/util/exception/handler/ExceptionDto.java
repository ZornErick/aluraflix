package br.com.alura.aluraflix.util.exception.handler;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ExceptionDto {

    private final int status;
    private final HttpStatus error;
    private final String message;

    public ExceptionDto(HttpStatus error, String message) {
        this.status = error.value();
        this.error = error;
        this.message = message;
    }
}
