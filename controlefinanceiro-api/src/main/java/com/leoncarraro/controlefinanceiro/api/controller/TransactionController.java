package com.leoncarraro.controlefinanceiro.api.controller;

import com.leoncarraro.controlefinanceiro.api.controller.util.URIParamConverter;
import com.leoncarraro.controlefinanceiro.api.dto.GeneralStatistic;
import com.leoncarraro.controlefinanceiro.api.dto.TransactionRequest;
import com.leoncarraro.controlefinanceiro.api.dto.TransactionResponse;
import com.leoncarraro.controlefinanceiro.api.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/transactions")
@AllArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;
    private final URIParamConverter uriParamConverter;

    @GetMapping
    public ResponseEntity<Page<TransactionResponse>> getAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "6") Integer size,
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "wallets", defaultValue = "") String wallets
    ) {
        String nameDecoded = uriParamConverter.decodeParam(name);
        List<Long> ids = uriParamConverter.getListOfIds(wallets);

        return ResponseEntity.ok(transactionService.getAll(page, size, nameDecoded, ids));
    }

    @PostMapping
    public ResponseEntity<TransactionResponse> create(@RequestBody @Valid TransactionRequest transactionRequest) {
        TransactionResponse transactionResponse = transactionService.create(transactionRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(transactionResponse.getId())
                .toUri();

        return ResponseEntity.created(location).body(transactionResponse);
    }

    @GetMapping(value = "/statistics/general")
    public ResponseEntity<GeneralStatistic> getGeneralStatistics() {
        GeneralStatistic generalStatistic = transactionService.getGeneralStatistics();
        return ResponseEntity.ok(generalStatistic);
    }

}
