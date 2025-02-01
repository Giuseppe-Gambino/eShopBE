package it.epicode.eShop.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotEmpty(message = "Il username non può essere vuoto")
    private String username;
    @NotEmpty(message = "Il password non può essere vuoto")
    @Size(min = 4, message = "La lunghezza della pass deve essere almeno 4 caratteri")
    private String password;
    @Email(message = "Inserisci un'email valida")
    private String email;
    private String nome;
    private String cognome;
}
