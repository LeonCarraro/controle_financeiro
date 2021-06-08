package com.leoncarraro.controlefinanceiro.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class TransactionResponse {

    private Long id;
    private String description;
    private BigDecimal value;
    private LocalDate insertionDate;
    private String walletName;
    private String categoryName;

}
