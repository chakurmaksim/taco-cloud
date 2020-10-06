package com.training.tacos.data.repository;

import com.training.tacos.data.model.Ingredient;

import java.util.List;

public interface IngredientRepository {

    List<Ingredient> findAll();

    Ingredient findOne(String id);

    Ingredient save(Ingredient ingredient);
}
