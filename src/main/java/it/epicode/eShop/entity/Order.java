package it.epicode.eShop.entity;

import it.epicode.eShop.auth.AppUser;
import it.epicode.eShop.enums.StatusOrder;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.engine.spi.Status;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "Orders")
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "app_user_id")
    private AppUser appUser;

    @Column(name = "total", precision = 19, scale = 2)
    private BigDecimal total;

    @Enumerated
    @Column(name = "status")
    private StatusOrder status;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @OneToMany(mappedBy = "order", orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    @Column(name = "stripeSessionId")
    private String stripeSessionId;

}
