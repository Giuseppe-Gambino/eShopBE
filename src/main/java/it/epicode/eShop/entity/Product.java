package it.epicode.eShop.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import it.epicode.eShop.auth.AppUser;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Data
@Table(name = "products")
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "description_seconda", columnDefinition = "text")
    private String descriptionSeconda;

    @Column(name = "description_terza", columnDefinition = "text")
    private String descriptionTerza;


    @Column(name = "price", precision = 19, scale = 2)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "reseller_id")
    private AppUser reseller;

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

    @JsonGetter("resellerId")
    public Long getResellerId() {
        return reseller != null ? reseller.getId() : null;
    }

    @JsonIgnore
    public AppUser getReseller() {
        return reseller;
    }

}
