package it.epicode.eShop.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "Categorie")
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

}
