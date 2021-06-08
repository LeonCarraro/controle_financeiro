package com.leoncarraro.controlefinanceiro.api.dto.assembler;

import com.leoncarraro.controlefinanceiro.api.dto.CategoryRequest;
import com.leoncarraro.controlefinanceiro.api.model.Category;
import com.leoncarraro.controlefinanceiro.api.model.User;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CategoryRequestDisassembler {

    private final ModelMapper modelMapper;

    public Category toModel(CategoryRequest categoryRequest, User user) {
        Category category = modelMapper.map(categoryRequest, Category.class);
        category.setUser(user);

        return category;
    }

}
