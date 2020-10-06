package com.training.tacos.controller;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.training.tacos.data.model.Ingredient;
import com.training.tacos.data.model.Ingredient.Type;
import com.training.tacos.data.repository.IngredientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

@ExtendWith(SpringExtension.class)
@WebMvcTest(DesignTacoController.class)
class DesignTacoControllerTest {

    @MockBean
    private IngredientRepository ingredientRepo;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        Ingredient ingredient = new Ingredient("FLTO", "Flour Tortilla", Type.WRAP);
        when(ingredientRepo.findAll()).thenReturn(Arrays.asList(ingredient));
    }

    @Test
    public void testDesignPage() throws Exception {
        mockMvc.perform(get("/design"))
                .andExpect(status().isOk())
                .andExpect(view().name("design"))
                .andExpect(content().string(containsString("Design your taco!")));
    }
}