package it.epicode.eShop.dto;

import lombok.Data;

@Data
public class OrderSummaryDTO {
    private int totalOrders;
    private int inElaborazione;
    private int spedito;
    private int consegnato;

}
