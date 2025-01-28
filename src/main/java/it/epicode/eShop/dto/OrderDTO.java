package it.epicode.eShop.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class OrderDTO {
    private Long id;
    private Long userId;
    private BigDecimal totalAmount;
    private LocalDate orderDate;
    private String status;
}
