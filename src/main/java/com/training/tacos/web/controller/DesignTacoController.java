package com.training.tacos.web.controller;

import com.training.tacos.service.dto.OrderDto;
import com.training.tacos.service.dto.TacoDto;
import com.training.tacos.data.model.Ingredient;
import com.training.tacos.data.model.Ingredient.Type;
import com.training.tacos.service.DesignTacoService;
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
import javax.validation.Valid;
import java.security.Principal;
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
    private final DesignTacoService designTacoService;

    @Autowired
    public DesignTacoController(DesignTacoService designTacoService) {
        this.designTacoService = designTacoService;
    }

    @ModelAttribute(name = "order")
    public OrderDto order() {
        return new OrderDto();
    }

    @GetMapping
    public String showDesignForm(Model model) {
        log.info("Design form was inquired");
        addTypeAttributes(model);
        model.addAttribute(DESIGN_ATTRIBUTE, new TacoDto());
        return DESIGN_FORM;
    }

    @PostMapping
    public String processDesign(@Valid @ModelAttribute("design") TacoDto design, Errors errors,
                                Model model, @ModelAttribute("order") OrderDto order, Principal principal) {
        if (errors.hasErrors()) {
            log.error(errors.toString());
            addTypeAttributes(model);
            return DESIGN_FORM;
        }
        log.info("Processing design: " + design);
        TacoDto saved = designTacoService.saveTaco(design);
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
            model.addAttribute(type.toString().toLowerCase(), filterByType(designTacoService.getAllIngredients(), type));
        }
    }

    private void addDesign(OrderDto order, TacoDto taco) {
        List<TacoDto> tacos = order.getTacos();
        if (tacos == null) {
            tacos = new ArrayList<>();
        }
        tacos.add(taco);
        order.setTacos(tacos);
    }
}
