package com.training.tacos.service.mapper;

import com.training.tacos.data.model.Ingredient;
import com.training.tacos.data.model.Taco;
import com.training.tacos.service.dto.TacoDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TacoMapper {

    private ModelMapper modelMapper;

    @Autowired
    public TacoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Taco convertToEntity(TacoDto tacoDto, List<Ingredient> ingredients) {
        Taco taco = modelMapper.map(tacoDto, Taco.class);
        List<Ingredient> tacosIngredients = new ArrayList<>();
        for (String id : tacoDto.getIngredientIds()) {
            Ingredient tacosIngredient = ingredients.stream().filter(ingredient -> ingredient.getId().equals(id))
                    .findFirst().orElseThrow();
            tacosIngredients.add(tacosIngredient);
        }
        taco.setIngredients(tacosIngredients);
        return taco;
    }

    public TacoDto convertToDto(Taco taco) {
        TacoDto tacoDto = modelMapper.map(taco, TacoDto.class);
        List<String> ingredientIds = taco.getIngredients().stream().map(Ingredient::getId).collect(Collectors.toList());
        tacoDto.setIngredientIds(ingredientIds);
        return tacoDto;
    }
}
