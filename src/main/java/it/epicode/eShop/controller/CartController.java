package it.epicode.eShop.controller;

import it.epicode.eShop.entity.Cart;
import it.epicode.eShop.services.CartSvc;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartSvc cartSvc;

    @PostMapping("/add")
    public ResponseEntity<Cart> addProductToCart(
            @RequestParam Long productId,
            @RequestParam int quantity,
            @AuthenticationPrincipal UserDetails user) {

        Cart updatedCart = cartSvc.addProductToCart(productId, quantity, user);
        return ResponseEntity.ok(updatedCart);
    }
}
