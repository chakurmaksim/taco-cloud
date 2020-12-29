package com.training.tacos.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class IngredientDto {

    private String id;
    @NotNull
    @Size(min=5, message="Name must be at least 5 characters long")
    private String name;
    private String type;
}
