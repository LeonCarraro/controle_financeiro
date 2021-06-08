package com.leoncarraro.controlefinanceiro.api.dto;

import com.leoncarraro.controlefinanceiro.api.model.Transaction;
import com.leoncarraro.controlefinanceiro.api.model.User;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
public class GeneralStatistic {

    private String username;
    private List<WalletResponse> wallets;
    private BigDecimal totalBalance;
    private List<StatisticByCategory> positiveStatisticByCategory;
    private List<StatisticByCategory> negativeStatisticByCategory;

    public GeneralStatistic(User user) {
        username = user.getUsername();
        wallets = user.getWallets().stream()
                .map(WalletResponse::new)
                .collect(Collectors.toList());

        totalBalance = wallets.stream()
                .map(WalletResponse::getBalance)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        positiveStatisticByCategory = user.getCategories().stream()
                .filter(category -> !category.getTransactions().isEmpty())
                .map(category -> category.getTransactions().stream()
                        .map(Transaction::getValue)
                        .reduce(BigDecimal::add)
                        .map(value -> new StatisticByCategory(category.getName(), value)))
                .sorted((o1, o2) -> o2.get().getValue().compareTo(o1.get().getValue()))
                .filter(obj -> obj.get().value.compareTo(BigDecimal.ZERO) > 0)
                .limit(5)
                .map(Optional::get)
                .collect(Collectors.toList());

        negativeStatisticByCategory = user.getCategories().stream()
                .filter(category -> !category.getTransactions().isEmpty())
                .map(category -> category.getTransactions().stream()
                        .map(Transaction::getValue)
                        .reduce(BigDecimal::add)
                        .map(value -> new StatisticByCategory(category.getName(), value)))
                .sorted(Comparator.comparing(o -> o.get().getValue()))
                .filter(obj -> obj.get().value.compareTo(BigDecimal.ZERO) < 0)
                .limit(5)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    @Getter
    public class StatisticByCategory {

        private String categoryName;
        private BigDecimal value;

        public StatisticByCategory(String categoryName, BigDecimal value) {
            this.categoryName = categoryName;
            this.value = value;
        }

    }

}
