package com.leoncarraro.controlefinanceiro.api.dto;

import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
public class CategoryRequest {

    @NotBlank(message = "O campo Nome é de preenchimento obrigatório")
    @Length(min = 5, max = 50, message = "O campo Nome deve conter entre 5 e 50 caracteres")
    private String name;

}
