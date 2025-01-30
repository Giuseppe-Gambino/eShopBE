package it.epicode.eShop.controller;

import it.epicode.eShop.dto.UsersPromotionsRequestsDTO;
import it.epicode.eShop.entity.UsersPromotionsRequests;
import it.epicode.eShop.services.UsersPromotionsRequestsSvc;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipal;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/UsersPromotionsRequests")
public class UsersPromotionsRequestsController {
    private final UsersPromotionsRequestsSvc promotionsSvc;

    @PostMapping
    public ResponseEntity<UsersPromotionsRequests> create(@RequestBody UsersPromotionsRequestsDTO usersPromotionsRequestsDTO, @AuthenticationPrincipal UserDetails user) {
        return new ResponseEntity<>(promotionsSvc.create(usersPromotionsRequestsDTO,user.getUsername()), HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<UsersPromotionsRequests>> findAll() {
        return ResponseEntity.ok(promotionsSvc.findAll());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> delete(@PathVariable Long id) {
       promotionsSvc.delete(id);
        return new ResponseEntity<>("Cliente eliminato correttamente!",HttpStatus.NO_CONTENT);
    }

    @PutMapping("/mark/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<UsersPromotionsRequests> editMark(@PathVariable Long id,@RequestParam Boolean mark) {
        return ResponseEntity.ok(promotionsSvc.editMark(id,mark));
    }

    @GetMapping("/findByUsername")
    public ResponseEntity<List<UsersPromotionsRequests>> findByUsername(@RequestParam String username) {
        return ResponseEntity.ok(promotionsSvc.findByUsername(username));
    }

}
