package com.leoncarraro.controlefinanceiro.api.service.exceptions;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String msg) {
        super(msg);
    }

}
