package com.leoncarraro.controlefinanceiro.api.controller.exceptions;

import com.leoncarraro.controlefinanceiro.api.service.exceptions.BadRequestException;
import com.leoncarraro.controlefinanceiro.api.service.exceptions.UserAlreadyExistsException;
import com.leoncarraro.controlefinanceiro.api.service.exceptions.WalletNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    public ResponseEntity<StandardError> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        List<String> errors = e.getBindingResult().getFieldErrors()
                .stream().map(f -> f.getDefaultMessage()).collect(Collectors.toList());

        StandardError error = new StandardError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                errors
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(value = { MethodArgumentTypeMismatchException.class })
    public ResponseEntity<StandardError> methodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException e) {
        String name = e.getName().toUpperCase();
        Object value = e.getValue();
        String message = String.format("Busca pelo(a) %s '%s' não é permitida!", name, value);

        StandardError error = new StandardError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                Arrays.asList(message)
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(value = { HttpMessageNotReadableException.class })
    public ResponseEntity<StandardError> httpMessageNotRedeableExceptionHandler(HttpMessageNotReadableException e) {
        StandardError error = new StandardError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                Arrays.asList("O corpo da requisição contém 1 ou mais atributos inválidos! Verifique erro de sintaxe e tente novamente.")
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(value = { BadRequestException.class })
    public ResponseEntity<StandardError> badRequestExceptionHandler(BadRequestException e) {
        StandardError error = new StandardError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                Arrays.asList(e.getMessage())
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(value = { WalletNotFoundException.class })
    public ResponseEntity<StandardError> resourceNotFoundExceptionHandler(RuntimeException e) {
        StandardError error = new StandardError(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                Arrays.asList(e.getMessage())
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(value = { UserAlreadyExistsException.class })
    public ResponseEntity<StandardError> userAlreadyExistsExceptionHandler(UserAlreadyExistsException e) {
        StandardError error = new StandardError(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                Arrays.asList(e.getMessage())
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

}
