package com.leoncarraro.controlefinanceiro.api.dto;

import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
public class WalletRequest {

    @NotBlank(message = "O campo Nome é de preenchimento obrigatório")
    @Length(min = 5, max = 50, message = "O campo Nome deve conter entre 5 e 50 caracteres")
    private String name;

    @DecimalMin(value = "-9999999999.99", message = "Valor Inicial mínimo excedido. É permitido um valor entre -9999999999.99 e 9999999999.99")
    @DecimalMax(value = "9999999999.99", message = "Valor Inicial máximo excedido. É permitido um valor entre -9999999999.99 e 9999999999.99")
    @NotNull(message = "O campo Valor Inicial é de preenchimento obrigatório")
    private BigDecimal initialBalance;

}
