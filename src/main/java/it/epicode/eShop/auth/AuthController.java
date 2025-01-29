package it.epicode.eShop.auth;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AppUserService appUserService;

    @PostMapping("/register")
    public ResponseEntity<AppUser> register(@RequestBody RegisterRequest registerRequest) {
        AppUser newUser = appUserService.registerUser(
                registerRequest,
                Set.of(Role.ROLE_USER) // Assegna il ruolo di default
        );
        return ResponseEntity.ok(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        AuthResponse authResponse = appUserService.authenticateUser(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        );
        return ResponseEntity.ok(authResponse);
    }

    @GetMapping
    public AppUser getUser(@AuthenticationPrincipal UserDetails user) {
        return appUserService.loadUserByUsername(user.getUsername());
    }

    @PutMapping("/seller")
    public AppUser updateToSeller(@AuthenticationPrincipal UserDetails user) {
                return appUserService.updateToSeller(user.getUsername());
    }

    @PutMapping("/admin")
    public AppUser updateToAdmin(@AuthenticationPrincipal UserDetails user) {
        return appUserService.updateToAdmin(user.getUsername());
    }

    @PutMapping("/removeSeller")
    public AppUser removeSeller(@AuthenticationPrincipal UserDetails user) {
        return appUserService.removeSeller(user.getUsername());
    }

    @PutMapping("/removeAdmin")
    public AppUser removeAdmin(@AuthenticationPrincipal UserDetails user) {
        return appUserService.removeAdmin(user.getUsername());
    }
}

