package com.training.tacos.data.dto;

import lombok.Data;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class OrderDto {

    private Long id;
    @NotNull
    @Size(min=3, message="Name must be at least 3 characters long")
    private String deliveryName;
    @NotEmpty(message="Street is required")
    private String deliveryStreet;
    @NotEmpty(message = "City is required")
    private String deliveryCity;
    @NotEmpty(message="State is required")
    private String deliveryState;
    @NotEmpty(message="Zip code is required")
    private String deliveryZip;
    @NotEmpty(message="Not a valid credit card number")
    private String ccNumber;
    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
            message="Must be formatted MM/YY")
    private String ccExpiration;
    @Digits(integer=3, fraction=0, message="Invalid CVV")
    private String ccCVV;
    private List<TacoDto> tacos;
}
