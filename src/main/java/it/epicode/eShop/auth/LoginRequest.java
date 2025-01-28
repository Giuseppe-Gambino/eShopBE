package it.epicode.eShop.auth;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
