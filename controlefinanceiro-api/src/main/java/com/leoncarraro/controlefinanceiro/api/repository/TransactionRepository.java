package com.leoncarraro.controlefinanceiro.api.repository;

import com.leoncarraro.controlefinanceiro.api.model.Transaction;
import com.leoncarraro.controlefinanceiro.api.model.User;
import com.leoncarraro.controlefinanceiro.api.model.Wallet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Page<Transaction> findAllByUserAndWalletInAndDescriptionContaining(User user, List<Wallet> wallets, String description, Pageable request);

}
