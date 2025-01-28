package it.epicode.eShop.dto;

import it.epicode.eShop.entity.Category;
import lombok.Data;

import java.math.BigDecimal;


@Data
public class ProductDTO {
    private String name;
    private String description;
    private BigDecimal price;
}
