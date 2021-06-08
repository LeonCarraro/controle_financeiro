package com.leoncarraro.controlefinanceiro.api.dto.assembler;

import com.leoncarraro.controlefinanceiro.api.dto.WalletRequest;
import com.leoncarraro.controlefinanceiro.api.model.User;
import com.leoncarraro.controlefinanceiro.api.model.Wallet;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class WalletRequestDisassembler {

    private final ModelMapper modelMapper;

    public Wallet toModel(WalletRequest walletRequest, User user) {
        Wallet wallet = modelMapper.map(walletRequest, Wallet.class);
        wallet.setBalance(walletRequest.getInitialBalance());
        wallet.setUser(user);

        return wallet;
    }

}
