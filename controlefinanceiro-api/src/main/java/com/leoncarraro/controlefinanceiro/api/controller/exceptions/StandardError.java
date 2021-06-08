package com.leoncarraro.controlefinanceiro.api.controller.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class StandardError {

    private LocalDateTime timeStamp;
    private Integer statusCode;
    private String statusDescription;
    private List<String> errors;

}
