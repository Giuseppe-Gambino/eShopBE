package it.epicode.eShop.auth;

import lombok.Data;

@Data
public class UserUpdateDTO {
    private String nome;
    private String cognome;
    private String email;
}
