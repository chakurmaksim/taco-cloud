package com.training.tacos.service.dto;

import java.util.List;

public class IngredientList {

    private List<IngredientDto> ingredients;

    public IngredientList() {}

    public IngredientList(List<IngredientDto> ingredients) {
        this.ingredients = ingredients;
    }

    public List<IngredientDto> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientDto> ingredients) {
        this.ingredients = ingredients;
    }
}
