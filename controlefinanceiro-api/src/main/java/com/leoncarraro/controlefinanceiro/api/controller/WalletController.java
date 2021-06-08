package com.leoncarraro.controlefinanceiro.api.controller;

import com.leoncarraro.controlefinanceiro.api.dto.WalletRequest;
import com.leoncarraro.controlefinanceiro.api.dto.WalletResponse;
import com.leoncarraro.controlefinanceiro.api.service.WalletService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/wallets")
@AllArgsConstructor
public class WalletController {

    private final WalletService walletService;

    @GetMapping
    public ResponseEntity<List<WalletResponse>> getAll() {
        return ResponseEntity.ok(walletService.getAll());
    }

    @PostMapping
    public ResponseEntity<WalletResponse> create(@RequestBody @Valid WalletRequest walletRequest) {
        WalletResponse walletResponse = walletService.create(walletRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(walletResponse.getId())
                .toUri();

        return ResponseEntity.created(location).body(walletResponse);
    }

}
