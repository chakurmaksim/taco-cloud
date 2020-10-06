package com.training.tacos.controller;

import com.training.tacos.data.model.Ingredient;
import com.training.tacos.data.model.Ingredient.Type;
import com.training.tacos.data.model.Order;
import com.training.tacos.data.model.Taco;
import com.training.tacos.data.repository.IngredientRepository;
import com.training.tacos.data.repository.TacoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private static final String DESIGN_FORM = "design";
    private static final String DESIGN_ATTRIBUTE = "design";
    private static List<Ingredient> ingredients;
    private final IngredientRepository ingredientRepo;
    private final TacoRepository tacoRepository;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository tacoRepository) {
        this.ingredientRepo = ingredientRepo;
        this.tacoRepository = tacoRepository;
    }

    @PostConstruct
    public void initIngredients() {
        ingredients = ingredientRepo.findAll();
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @GetMapping
    public String showDesignForm(Model model) {
        log.info("Design form was inquired");
        addTypeAttributes(model);
        model.addAttribute(DESIGN_ATTRIBUTE, new Taco());
        return DESIGN_FORM;
    }

    @PostMapping
    public String processDesign(@Valid @ModelAttribute("design") Taco design, Errors errors,
                                Model model, @ModelAttribute Order order) {
        if (errors.hasErrors()) {
            log.error(errors.toString());
            addTypeAttributes(model);
            return DESIGN_FORM;
        }
        log.info("Processing design: " + design);
        Taco saved = tacoRepository.save(design);
        addDesign(order, saved);
        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream().filter(ingredient -> ingredient.getType().equals(type))
                .collect(Collectors.toList());
    }

    private void addTypeAttributes(Model model) {
        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
    }

    private void addDesign(Order order, Taco taco) {
        List<Taco> tacos = order.getTacos();
        if (tacos == null) {
            tacos = new ArrayList<>();
        }
        tacos.add(taco);
        order.setTacos(tacos);
    }
}
