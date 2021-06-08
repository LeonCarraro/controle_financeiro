package com.leoncarraro.controlefinanceiro.api.dto.assembler;

import com.leoncarraro.controlefinanceiro.api.dto.WalletResponse;
import com.leoncarraro.controlefinanceiro.api.model.Wallet;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class WalletResponseAssembler {

    private final ModelMapper modelMapper;

    public WalletResponse toResponse(Wallet wallet) {
        return modelMapper.map(wallet, WalletResponse.class);
    }

    public List<WalletResponse> toResponse(List<Wallet> wallets) {
        return wallets.stream().map(w -> toResponse(w)).collect(Collectors.toList());
    }

}
