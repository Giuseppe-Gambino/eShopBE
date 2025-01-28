package it.epicode.eShop.dto;

import lombok.Data;
import org.hibernate.engine.spi.Status;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class OrderDTO {
    private Long userId;
    private BigDecimal totalAmount;
    private LocalDate orderDate;
    private Status status;
}
