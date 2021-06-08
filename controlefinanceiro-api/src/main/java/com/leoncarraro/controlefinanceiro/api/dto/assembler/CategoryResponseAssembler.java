package com.leoncarraro.controlefinanceiro.api.dto.assembler;

import com.leoncarraro.controlefinanceiro.api.dto.CategoryResponse;
import com.leoncarraro.controlefinanceiro.api.model.Category;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CategoryResponseAssembler {

    private final ModelMapper modelMapper;

    public CategoryResponse toResponse(Category category) {
        return modelMapper.map(category, CategoryResponse.class);
    }

    public List<CategoryResponse> toResponse(List<Category> category) {
        return category.stream().map(c -> toResponse(c)).collect(Collectors.toList());
    }

}
