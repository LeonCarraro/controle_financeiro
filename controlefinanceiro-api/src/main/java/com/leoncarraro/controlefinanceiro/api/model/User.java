package com.leoncarraro.controlefinanceiro.api.model;

import com.leoncarraro.controlefinanceiro.api.dto.UserRequest;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_user")
@Getter
@Setter
@EqualsAndHashCode
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Category> categories;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Wallet> wallets;

    public User() {
    }

    public User(UserRequest userRequest, BCryptPasswordEncoder encoder) {
        username = userRequest.getUsername();
        password = encoder.encode(userRequest.getPassword());
        categories = new ArrayList<>();
        wallets = new ArrayList<>();
    }

}
