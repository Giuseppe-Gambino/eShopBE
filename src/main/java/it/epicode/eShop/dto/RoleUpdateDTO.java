package it.epicode.eShop.dto;

import it.epicode.eShop.auth.Role;
import lombok.Data;

import java.util.Set;

@Data
public class RoleUpdateDTO {
    private Long idUser;
    private Set<Role> rolesToAdd;
    private Set<Role> rolesToRemove;
}
