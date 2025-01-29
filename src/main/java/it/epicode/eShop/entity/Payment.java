package it.epicode.eShop.entity;

import it.epicode.eShop.enums.PaymentMethods;
import it.epicode.eShop.enums.PaymentStatus;
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
    private PaymentMethods paymentMethods;

    @Enumerated
    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;

    @Column(name = "payment_date")
    private LocalDate paymentDate;

}
