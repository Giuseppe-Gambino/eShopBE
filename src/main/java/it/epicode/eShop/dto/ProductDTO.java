package it.epicode.eShop.dto;

import it.epicode.eShop.entity.Category;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class ProductDTO {
    private String name;
    private String description;
    private BigDecimal price;
    private Category category;
}
