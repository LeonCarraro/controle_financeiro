package com.leoncarraro.controlefinanceiro.api.dto.assembler;

import com.leoncarraro.controlefinanceiro.api.dto.TransactionResponse;
import com.leoncarraro.controlefinanceiro.api.model.Transaction;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TransactionResponseAssembler {

    private final ModelMapper modelMapper;

    public TransactionResponse toResponse(Transaction transaction) {
        return modelMapper.map(transaction, TransactionResponse.class);
    }

    public Page<TransactionResponse> toResponse(Page<Transaction> transactions) {
        return transactions.map(t -> modelMapper.map(t, TransactionResponse.class));
    }

}
