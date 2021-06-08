package com.leoncarraro.controlefinanceiro.api.repository;

import com.leoncarraro.controlefinanceiro.api.model.User;
import com.leoncarraro.controlefinanceiro.api.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

    Optional<Wallet> findByIdAndUser(Long id, User user);

    List<Wallet> findAllByUser(User user);
    List<Wallet> findAllByIdInAndUser(List<Long> ids, User user);

}
