package it.epicode.eShop.entity;

import it.epicode.eShop.auth.AppUser;
import it.epicode.eShop.enums.StatusResellerOrder;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;


@Data
@Table(name = "ResellerOrder")
@Entity
public class ResellerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "reseller_id", nullable = false)
    private AppUser reseller;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "order_item_id")
    private OrderItem orderItem;

    @Column(name = "created_at")
    private LocalDate createdAt;

    // Campo per lo stato (ORDINE_RICEVUTO, IN_ELABORAZIONE, SPEDITO, CONSEGNATO)
    private StatusResellerOrder orderStatus;


}