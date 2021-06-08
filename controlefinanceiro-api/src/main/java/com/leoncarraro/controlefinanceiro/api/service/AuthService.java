package com.leoncarraro.controlefinanceiro.api.service;

import com.leoncarraro.controlefinanceiro.api.dto.UserRequest;
import com.leoncarraro.controlefinanceiro.api.model.Category;
import com.leoncarraro.controlefinanceiro.api.model.User;
import com.leoncarraro.controlefinanceiro.api.model.Wallet;
import com.leoncarraro.controlefinanceiro.api.repository.UserRepository;
import com.leoncarraro.controlefinanceiro.api.service.exceptions.UserAlreadyExistsException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder encoder;

    public void signup(UserRequest userRequest) {
        if (userRepository.existsByUsername(userRequest.getUsername())) {
            throw new UserAlreadyExistsException("Usuario " + userRequest.getUsername() + " já cadastrado!");
        }

        User user = new User(userRequest, encoder);
        user.getWallets().add(new Wallet(user));
        user.getCategories().addAll(Arrays.asList(
                new Category(user, "Lazer"),
                new Category(user, "Alimentação"),
                new Category(user, "Transporte"),
                new Category(user, "Mercado"),
                new Category(user, "Investimentos"),
                new Category(user, "Saúde"),
                new Category(user, "Outros")));

        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User getCurrentUser() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario " + username + " não encontrado!"));
    }

}
