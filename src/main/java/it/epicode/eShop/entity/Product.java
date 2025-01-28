package it.epicode.eShop.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Table(name = "products")
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price", precision = 19, scale = 2)
    private BigDecimal price;

    @ElementCollection
    @Column(name = "image_url")
    @CollectionTable(name = "Product_imageUrls", joinColumns = @JoinColumn(name = "product_id"))
    private List<String> imageUrls = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @ElementCollection
    @CollectionTable(name = "price_history", joinColumns = @JoinColumn(name = "product_id"))
    @MapKeyColumn(name = "date_changed")
    @Column(name = "price")
    private Map<LocalDate, BigDecimal> priceHistory = new HashMap<>();

}
