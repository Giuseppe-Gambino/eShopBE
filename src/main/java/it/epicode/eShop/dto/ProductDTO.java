package it.epicode.eShop.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;


@Data
public class ProductDTO {
    @NotEmpty(message = "Il campo name non può essere vuoto")
    private String name;
    @NotEmpty(message = "Il campo descrizione non può essere vuoto")
    private String description;

    private String titleSeconda;

    private String descriptionSeconda;

    private String titleTerza;

    private String descriptionTerza;

    @Positive(message = "Il valore price deve essere positivo")
    private BigDecimal price;
}
