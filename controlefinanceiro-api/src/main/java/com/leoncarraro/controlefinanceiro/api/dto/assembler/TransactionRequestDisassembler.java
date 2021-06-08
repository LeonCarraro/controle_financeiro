package com.leoncarraro.controlefinanceiro.api.dto.assembler;

import com.leoncarraro.controlefinanceiro.api.dto.TransactionRequest;
import com.leoncarraro.controlefinanceiro.api.model.Category;
import com.leoncarraro.controlefinanceiro.api.model.Transaction;
import com.leoncarraro.controlefinanceiro.api.model.User;
import com.leoncarraro.controlefinanceiro.api.model.Wallet;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TransactionRequestDisassembler {

    private final ModelMapper modelMapper;

    public Transaction toModel(TransactionRequest transactionRequest, Wallet wallet, Category category, User user) {
        Transaction transaction = modelMapper.map(transactionRequest, Transaction.class);
        transaction.setWallet(wallet);
        transaction.setCategory(category);
        transaction.setUser(user);

        return transaction;
    }

}
