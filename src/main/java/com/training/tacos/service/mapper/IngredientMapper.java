package com.training.tacos.service.mapper;

import com.training.tacos.data.model.Ingredient;
import com.training.tacos.service.dto.IngredientDto;
import org.springframework.stereotype.Component;

@Component
public class IngredientMapper {

    public IngredientDto convertToDto(Ingredient ingredient) {
        IngredientDto ingredientDto = new IngredientDto();
        ingredientDto.setId(ingredient.getId());
        ingredientDto.setName(ingredient.getName());
        ingredientDto.setType(ingredient.getType().name());
        return ingredientDto;
    }
}
