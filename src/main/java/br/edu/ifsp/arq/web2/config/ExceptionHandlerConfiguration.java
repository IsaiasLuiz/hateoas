package br.edu.ifsp.arq.web2.config;

import br.edu.ifsp.arq.web2.domain.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandlerConfiguration extends ResponseEntityExceptionHandler {
    @Autowired
    MessageSource messageSource;

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity resourceNotFound(NotFoundException exception, final HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @Override
    public ResponseEntity handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                       HttpHeaders headers, HttpStatus status, WebRequest request) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        ex.getAllErrors().stream()
                                .map(e -> new StringBuilder()
                                        .append(messageSource.getMessage(((FieldError)e).getField(), null, LocaleContextHolder.getLocale()))
                                        .append(" ")
                                        .append(messageSource.getMessage(e, LocaleContextHolder.getLocale())))
                                .collect(Collectors.toList()).toString());
    }

}
