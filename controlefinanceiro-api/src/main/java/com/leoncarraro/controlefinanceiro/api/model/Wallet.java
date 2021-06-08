package com.leoncarraro.controlefinanceiro.api.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_wallet")
@Getter
@Setter
@EqualsAndHashCode
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal balance;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "wallet")
    private List<Transaction> transactions = new ArrayList<>();

    public BigDecimal getBalance() {
        BigDecimal total = balance;

        for (Transaction t : transactions) {
            total = total.add(t.getValue());
        }

        return total;
    }

    public Wallet() {
    }

    public Wallet(User user) {
        name = "Bolso";
        balance = BigDecimal.ZERO;
        this.user = user;
    }

}
