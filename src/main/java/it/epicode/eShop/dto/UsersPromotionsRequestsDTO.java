package it.epicode.eShop.dto;

import it.epicode.eShop.auth.AppUser;
import it.epicode.eShop.enums.ObjectPartner;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UsersPromotionsRequestsDTO {
    private ObjectPartner objectPartner;
    @NotEmpty(message = "Il campo descrizione non può essere vuoto")
    private String description;
}
