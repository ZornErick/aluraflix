package br.com.alura.aluraflix.util.exception.handler;

import br.com.alura.aluraflix.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionHandler {

    private final MessageSource messageSource;

    @Autowired
    public ExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class)
    public ExceptionDto handle(NotFoundException exception) {
        return new ExceptionDto(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ValidationFormExceptionDto> handle(MethodArgumentNotValidException exception) {
        List<ValidationFormExceptionDto> dto = new ArrayList<>();

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e -> {
            String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ValidationFormExceptionDto error = new ValidationFormExceptionDto(e.getField(), message);
            dto.add(error);
        });
        return dto;
    }

    @ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED)
    @org.springframework.web.bind.annotation.ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ExceptionDto handle(HttpRequestMethodNotSupportedException exception) {
        return new ExceptionDto(HttpStatus.METHOD_NOT_ALLOWED, exception.getMessage());
    }
}
