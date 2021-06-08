package com.leoncarraro.controlefinanceiro.api.service.exceptions;

public class WalletNotFoundException extends RuntimeException {

    public WalletNotFoundException(String msg) {
        super(msg);
    }

}
