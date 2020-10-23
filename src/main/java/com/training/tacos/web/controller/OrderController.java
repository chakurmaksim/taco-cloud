package com.training.tacos.web.controller;

import com.training.tacos.data.model.User;
import com.training.tacos.service.dto.OrderDto;
import com.training.tacos.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
    public String orderForm(Model model, @ModelAttribute("order") OrderDto order, Authentication authentication) {
        if (order == null) {
            model.addAttribute(ORDER_ATTRIBUTE, fillOrderFormWithUserInfo(new OrderDto(), authentication));
        }
        fillOrderFormWithUserInfo(order, authentication);
        return ORDER_FORM;
    }

    @PostMapping
    public String processOrder(@Valid @ModelAttribute("order") OrderDto order, Errors errors,
                               SessionStatus sessionStatus, Authentication authentication) {
        if (errors.hasErrors()) {
            return ORDER_FORM;
        }
        orderService.saveOrder(order);
        sessionStatus.setComplete();
        log.info("Order submitted: " + order);
        return "redirect:/";
    }

    private OrderDto fillOrderFormWithUserInfo(OrderDto order, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        order.setDeliveryName(user.getFullname());
        order.setDeliveryCity(user.getCity());
        order.setDeliveryState(user.getState());
        order.setDeliveryStreet(user.getStreet());
        order.setDeliveryZip(user.getZip());
        return order;
    }
}
