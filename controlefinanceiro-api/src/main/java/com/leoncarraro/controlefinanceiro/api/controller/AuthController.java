package com.leoncarraro.controlefinanceiro.api.controller;

import com.leoncarraro.controlefinanceiro.api.dto.UserRequest;
import com.leoncarraro.controlefinanceiro.api.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/signup")
    public ResponseEntity<Void> signup(@RequestBody @Valid UserRequest userRequest) {
        authService.signup(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
