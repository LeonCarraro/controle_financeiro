package com.leoncarraro.controlefinanceiro.api.repository;

import com.leoncarraro.controlefinanceiro.api.model.Category;
import com.leoncarraro.controlefinanceiro.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByIdAndUser(Long id, User user);

    List<Category> findAllByUser(User user);

}
