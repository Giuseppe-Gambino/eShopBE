package it.epicode.eShop.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class TicketDTO {
    @NotEmpty(message = "Il campo oggetto non può essere vuoto")
    private String object;
    @NotEmpty(message = "Il campo descrizione non può essere vuoto")
    private String description;
}
