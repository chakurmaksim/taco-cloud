package com.training.tacos.controller;

import com.training.tacos.data.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
public class OrderController {

    private static final String ORDER_ATTRIBUTE = "order";
    private static final String ORDER_FORM = "order";

    @GetMapping("/current")
    public String orderForm(Model model) {
        model.addAttribute(ORDER_ATTRIBUTE, new Order());
        return ORDER_FORM;
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors) {
        if (errors.hasErrors()) {
            return ORDER_FORM;
        }
        log.info("Order submitted: " + order);
        return "redirect:/";
    }
}