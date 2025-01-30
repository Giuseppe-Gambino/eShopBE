package it.epicode.eShop.dto;

import it.epicode.eShop.auth.AppUser;
import it.epicode.eShop.enums.ObjectPartner;
import lombok.Data;

@Data
public class UsersPromotionsRequestsDTO {
    private ObjectPartner objectPartner;
    private String description;
}
