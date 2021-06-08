package com.leoncarraro.controlefinanceiro.api.service.exceptions;

public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(String msg) {
        super(msg);
    }

}
