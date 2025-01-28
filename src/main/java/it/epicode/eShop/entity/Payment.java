package it.epicode.eShop.entity;

import it.epicode.eShop.enums.paymentMethods;
import it.epicode.eShop.enums.paymentStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Table(name = "Payments")
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "order_id")
    private Order order;

    @Enumerated
    @Column(name = "payment_methods")
    private paymentMethods paymentMethods;

    @Enumerated
    @Column(name = "payment_status")
    private it.epicode.eShop.enums.paymentStatus paymentStatus;

    @Column(name = "payment_date")
    private LocalDate paymentDate;

}
