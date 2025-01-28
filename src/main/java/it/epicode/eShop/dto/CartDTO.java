package it.epicode.eShop.dto;

import lombok.Data;

@Data
public class CartDTO {
    private Long id;
    private Long userId; // ID dell'utente a cui appartiene il carrello
}
