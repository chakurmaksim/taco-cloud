package com.training.tacos.web.controller;

import com.training.tacos.service.UserService;
import com.training.tacos.service.dto.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private static final String REGISTRATION_FORM = "registration";
    private static final String DESIGN_ATTRIBUTE = "registrationForm";
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String registerForm(Model model) {
        model.addAttribute(DESIGN_ATTRIBUTE, new RegistrationForm());
        return REGISTRATION_FORM;
    }

    @PostMapping
    public String processRegistration(@Valid @ModelAttribute("registrationForm") RegistrationForm form, Errors errors) {
        if (errors.hasErrors()) {
            return REGISTRATION_FORM;
        }
        userService.saveUser(form, passwordEncoder);
        return "redirect:/login";
    }
}
