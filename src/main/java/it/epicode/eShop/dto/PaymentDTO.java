package it.epicode.eShop.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PaymentDTO {
    private Long id;
    private Long orderId; // ID dell'ordine associato al pagamento
    private String paymentMethod; // Metodo di pagamento (es. CREDIT_CARD)
    private String paymentStatus; // Stato del pagamento (es. SUCCESS)
    private LocalDate paymentDate;
}