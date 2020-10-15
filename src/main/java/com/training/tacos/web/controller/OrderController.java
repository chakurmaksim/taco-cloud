package com.training.tacos.web.controller;

import com.training.tacos.service.dto.OrderDto;
import com.training.tacos.service.OrderService;
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
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

    private static final String ORDER_ATTRIBUTE = "order";
    private static final String ORDER_FORM = "order";
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/current")
    public String orderForm(Model model, @ModelAttribute("order") OrderDto order) {
        if (order == null) {
            model.addAttribute(ORDER_ATTRIBUTE, new OrderDto());
        }
        return ORDER_FORM;
    }

    @PostMapping
    public String processOrder(@Valid @ModelAttribute("order") OrderDto order, Errors errors,
                               SessionStatus sessionStatus) {
        if (errors.hasErrors()) {
            return ORDER_FORM;
        }
        orderService.saveOrder(order);
        sessionStatus.setComplete();
        log.info("Order submitted: " + order);
        return "redirect:/";
    }
}
