package it.epicode.eShop.auth;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public ResponseEntity<AppUser> getUser(@AuthenticationPrincipal UserDetails user) {
        return ResponseEntity.ok(appUserService.loadUserByUsername(user.getUsername()));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<AppUser>> getUser() {
        return ResponseEntity.ok(appUserService.findAll());
    }



    @PutMapping("/promoteSeller/{idUser}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<AppUser> updateToSeller(@PathVariable Long idUser) {
                return ResponseEntity.ok(appUserService.updateToSeller(idUser));
    }

    @PutMapping("/promoteAdmin/{idUser}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<AppUser> updateToAdmin(@PathVariable Long idUser) {
        return ResponseEntity.ok(appUserService.updateToAdmin(idUser));
    }

    @PutMapping("/removeSeller/{idUser}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<AppUser> removeSeller(@PathVariable Long idUser) {
        return ResponseEntity.ok(appUserService.removeSeller(idUser));
    }

    @PutMapping("/removeAdmin/{idUser}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<AppUser> removeAdmin(@PathVariable Long idUser) {
        return ResponseEntity.ok(appUserService.removeAdmin(idUser));
    }
}

