package com.leoncarraro.controlefinanceiro.api.service;

import com.leoncarraro.controlefinanceiro.api.dto.CategoryRequest;
import com.leoncarraro.controlefinanceiro.api.dto.CategoryResponse;
import com.leoncarraro.controlefinanceiro.api.dto.assembler.CategoryRequestDisassembler;
import com.leoncarraro.controlefinanceiro.api.dto.assembler.CategoryResponseAssembler;
import com.leoncarraro.controlefinanceiro.api.model.Category;
import com.leoncarraro.controlefinanceiro.api.model.User;
import com.leoncarraro.controlefinanceiro.api.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final AuthService authService;

    private final CategoryRequestDisassembler categoryRequestDisassembler;
    private final CategoryResponseAssembler categoryResponseAssembler;

    @Transactional(readOnly = true)
    public List<CategoryResponse> getAll() {
        return categoryResponseAssembler.toResponse(categoryRepository.findAllByUser(authService.getCurrentUser()));
    }

    @Transactional
    public CategoryResponse create(CategoryRequest categoryRequest) {
        User user = authService.getCurrentUser();

        Category category = categoryRequestDisassembler.toModel(categoryRequest, user);

        return categoryResponseAssembler.toResponse(categoryRepository.save(category));
    }

}
