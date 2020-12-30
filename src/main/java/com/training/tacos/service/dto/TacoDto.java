package com.training.tacos.service.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Data
@RequiredArgsConstructor
public class TacoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    @NotNull
    @Size(min=5, message="Name must be at least 5 characters long")
    private String name;
    @NotNull(message = "You must choose at least 1 ingredient")
    private List<String> ingredientIds;
}
