package it.epicode.eShop.entity;

import it.epicode.eShop.auth.AppUser;
import it.epicode.eShop.enums.StatusTicket;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "app_user_id")
    private AppUser appUser;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "object")
    private String object;

    @Column(name = "description")
    private String description;

    @Enumerated
    @Column(name = "status_Ticket")
    private StatusTicket status;


}
