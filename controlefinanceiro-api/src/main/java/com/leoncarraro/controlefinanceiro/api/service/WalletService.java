package com.leoncarraro.controlefinanceiro.api.service;

import com.leoncarraro.controlefinanceiro.api.dto.WalletRequest;
import com.leoncarraro.controlefinanceiro.api.dto.WalletResponse;
import com.leoncarraro.controlefinanceiro.api.dto.assembler.WalletRequestDisassembler;
import com.leoncarraro.controlefinanceiro.api.dto.assembler.WalletResponseAssembler;
import com.leoncarraro.controlefinanceiro.api.model.User;
import com.leoncarraro.controlefinanceiro.api.model.Wallet;
import com.leoncarraro.controlefinanceiro.api.repository.WalletRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;

    private final AuthService authService;

    private final WalletRequestDisassembler walletRequestDisassembler;
    private final WalletResponseAssembler walletResponseAssembler;

    @Transactional(readOnly = true)
    public List<WalletResponse> getAll() {
        return walletResponseAssembler.toResponse(walletRepository.findAllByUser(authService.getCurrentUser()));
    }

    @Transactional
    public WalletResponse create(WalletRequest walletRequest) {
        User user = authService.getCurrentUser();

        Wallet wallet = walletRequestDisassembler.toModel(walletRequest, user);

        return walletResponseAssembler.toResponse(walletRepository.save(wallet));
    }

}
