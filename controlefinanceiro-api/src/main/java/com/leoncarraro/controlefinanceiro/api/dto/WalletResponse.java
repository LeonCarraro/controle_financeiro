package com.leoncarraro.controlefinanceiro.api.dto;

import com.leoncarraro.controlefinanceiro.api.model.Wallet;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class WalletResponse {

    private Long id;
    private String name;
    private BigDecimal balance;

    public WalletResponse() {
    }

    public WalletResponse(Wallet wallet) {
        id = wallet.getId();
        name = wallet.getName();
        balance = wallet.getBalance();
    }

}
