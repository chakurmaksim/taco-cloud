package com.training.tacos.service.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RegistrationForm {

    @NotNull
    @Size(min=3, message="Username must be at least 3 characters long")
    private String username;
    @NotNull
    @Size(min = 4, max = 10, message = "Password must be at least 4 symbols and not more than 10")
    private String password;
    @NotNull
    @Size(min=3, message="Name must be at least 3 characters long")
    private String fullname;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phone;
}
