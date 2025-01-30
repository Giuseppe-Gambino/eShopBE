package it.epicode.eShop.entity;

import it.epicode.eShop.auth.AppUser;
import it.epicode.eShop.enums.ObjectPartner;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class UsersPromotionsRequests {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "app_user_id")
    private AppUser appUser;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Enumerated
    @Column(name = "object_partner")
    private ObjectPartner objectPartner;

    @Column(name = "description")
    private String description;

    @Column(name = "mark")
    private Boolean mark;

}
