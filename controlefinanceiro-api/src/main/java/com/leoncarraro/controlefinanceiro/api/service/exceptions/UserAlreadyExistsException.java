package com.leoncarraro.controlefinanceiro.api.service.exceptions;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String msg) {
        super(msg);
    }

}
