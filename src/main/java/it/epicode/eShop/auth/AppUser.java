package it.epicode.eShop.auth;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;


@Entity
@Data
@Table(name = "users")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;


//    @Column(nullable = false, unique = true)
    private String email;

    private String nome;

    private String cognome;

    private String avatar;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

}
