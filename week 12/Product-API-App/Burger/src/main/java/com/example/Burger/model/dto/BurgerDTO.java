package com.example.Burger.model.dto;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BurgerDTO {
    private Long id;

    @NotNull
    @Size(min = 1, max = 50)
    private String name;
    private String location;
}
