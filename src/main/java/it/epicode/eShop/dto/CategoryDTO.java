package it.epicode.eShop.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CategoryDTO {
    @NotEmpty(message = "Il campo name non può essere vuoto")
    private String name;
}
