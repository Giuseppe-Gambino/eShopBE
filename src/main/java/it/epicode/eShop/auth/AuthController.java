package it.epicode.eShop.auth;

import it.epicode.eShop.dto.RoleUpdateDTO;
import it.epicode.eShop.entity.Product;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AppUserService appUserService;

    @PostMapping("/register")
    public ResponseEntity<AppUser> register(@Valid @RequestBody RegisterRequest registerRequest) {
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

    @GetMapping("/{id}")
    public ResponseEntity<AppUser> getById(@PathVariable Long id) {
        return ResponseEntity.ok(appUserService.findById(id));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<AppUser>> getUser() {
        return ResponseEntity.ok(appUserService.findAll());
    }

    @GetMapping("/findPageAllUsers")
    public ResponseEntity<Page<AppUser>> findPageUser(Pageable pageable) {
        return ResponseEntity.ok(appUserService.findPageUser(pageable));
    }

    @PatchMapping(path = "/user/img", consumes = {"multipart/form-data"})
    public ResponseEntity<AppUser> updateUserImage(@AuthenticationPrincipal UserDetails user, @RequestParam MultipartFile images) {
        AppUser appUser = appUserService.updateImg(user.getUsername(),images);
        return ResponseEntity.ok(appUser);
    }

    @PatchMapping(path = "/user/info")
    public ResponseEntity<AppUser> editUser(@AuthenticationPrincipal UserDetails user,@RequestBody UserUpdateDTO userUpdateDTO) {
        AppUser appUser = appUserService.editUser(user.getUsername(),userUpdateDTO);
        return ResponseEntity.ok(appUser);
    }


    @PostMapping("/updateRoles")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<AppUser> updateRoles(@RequestBody RoleUpdateDTO request) {
        AppUser updatedUser = appUserService.updateRoles(
                request.getIdUser(),
                request.getRolesToAdd(),
                request.getRolesToRemove()
        );
        return ResponseEntity.ok(updatedUser);
    }

}

