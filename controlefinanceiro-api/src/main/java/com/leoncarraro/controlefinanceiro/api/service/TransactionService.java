package com.leoncarraro.controlefinanceiro.api.service;

import com.leoncarraro.controlefinanceiro.api.dto.GeneralStatistic;
import com.leoncarraro.controlefinanceiro.api.dto.TransactionRequest;
import com.leoncarraro.controlefinanceiro.api.dto.TransactionResponse;
import com.leoncarraro.controlefinanceiro.api.dto.assembler.TransactionRequestDisassembler;
import com.leoncarraro.controlefinanceiro.api.dto.assembler.TransactionResponseAssembler;
import com.leoncarraro.controlefinanceiro.api.model.Category;
import com.leoncarraro.controlefinanceiro.api.model.Transaction;
import com.leoncarraro.controlefinanceiro.api.model.User;
import com.leoncarraro.controlefinanceiro.api.model.Wallet;
import com.leoncarraro.controlefinanceiro.api.repository.CategoryRepository;
import com.leoncarraro.controlefinanceiro.api.repository.TransactionRepository;
import com.leoncarraro.controlefinanceiro.api.repository.WalletRepository;
import com.leoncarraro.controlefinanceiro.api.service.exceptions.BadRequestException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;
    private final CategoryRepository categoryRepository;

    private final AuthService authService;

    private final TransactionRequestDisassembler transactionRequestDisassembler;
    private final TransactionResponseAssembler transactionResponseAssembler;

    @Transactional(readOnly = true)
    public Page<TransactionResponse> getAll(Integer page, Integer size, String name, List<Long> ids) {
        User user = authService.getCurrentUser();
        List<Wallet> wallets = walletRepository.findAllByIdInAndUser(ids, user);

        Sort sort = Sort.by(
                Sort.Order.desc("insertionDate"),
                Sort.Order.desc("id"));
        PageRequest pageRequest = PageRequest.of(page, size, sort);

        return transactionResponseAssembler
                .toResponse(transactionRepository.findAllByUserAndWalletInAndDescriptionContaining(user, wallets, name, pageRequest));
    }

    @Transactional
    public TransactionResponse create(TransactionRequest transactionRequest) {
        User user = authService.getCurrentUser();
        Wallet wallet = walletRepository.findByIdAndUser(transactionRequest.getWalletId(), user)
                .orElseThrow(() -> new BadRequestException("Carteira de código " + transactionRequest.getWalletId() +
                        " do usuário " + user.getUsername() + " não encontrada!"));
        Category category = categoryRepository.findByIdAndUser(transactionRequest.getCategoryId(), user)
                .orElseThrow(() -> new BadRequestException("Categoria de código " + transactionRequest.getCategoryId() +
                        " do usuário " + user.getUsername() + " não encontrada!"));

        Transaction transaction = transactionRequestDisassembler.toModel(transactionRequest, wallet, category, user);

        return transactionResponseAssembler.toResponse(transactionRepository.save(transaction));
    }

    @Transactional(readOnly = true)
    public GeneralStatistic getGeneralStatistics() {
        User user = authService.getCurrentUser();
        return new GeneralStatistic(user);
    }

}
