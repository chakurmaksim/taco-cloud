package com.training.tacos.web.controller;

import com.training.tacos.service.dto.IngredientDto;
import com.training.tacos.service.dto.IngredientList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.Optional;

@RestController
@RequestMapping("/rest/ingredients")
public class IngredientRestController {

    private static final String INGREDIENTS_URI = "http://localhost:8080/rest/design/ingredients";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{id}")
    public ResponseEntity<IngredientDto> findById(@PathVariable("id") String ingredientId) {
        IngredientList response = restTemplate.getForObject(INGREDIENTS_URI, IngredientList.class);
        Optional<IngredientDto> optionalIngredient = response.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId)).findFirst();
        return optionalIngredient.map(ingredientDto -> new ResponseEntity<>(ingredientDto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }
}
