package com.leoncarraro.controlefinanceiro.api.dto;

import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
public class UserRequest {

    @NotBlank(message = "O campo Nome é de preenchimento obrigatório")
    @Length(min = 5, max = 18, message = "O campo Nome deve conter entre 5 e 18 caracteres")
    private String username;

    @NotBlank(message = "O campo Senha é de preenchimento obrigatório")
    @Length(min = 5, max = 18, message = "O campo Senha deve conter entre 5 e 18 caracteres")
    private String password;

}
