package com.leoncarraro.controlefinanceiro.api.dto;

import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
public class TransactionRequest {

    @NotBlank(message = "O campo Descrição é de preenchimento obrigatório")
    @Length(min = 5, max = 50, message = "O campo Descrição deve conter entre 5 e 50 caracteres")
    private String description;

    @DecimalMin(value = "-9999999999.99", message = "Valor mínimo excedido. É permitido um valor entre -9999999999.99 e 9999999999.99")
    @DecimalMax(value = "9999999999.99", message = "Valor máximo excedido. É permitido um valor entre -9999999999.99 e 9999999999.99")
    @NotNull(message = "O campo Valor é de preenchimento obrigatório")
    private BigDecimal value;

    @NotNull(message = "O campo Carteira é de preenchimento obrigatório")
    private Long walletId;

    @NotNull(message = "O campo Categoria é de preenchimento obrigatório")
    private Long categoryId;

    @NotNull(message = "O campo Data é de preenchimento obrigatório")
    private LocalDate insertionDate;

}
