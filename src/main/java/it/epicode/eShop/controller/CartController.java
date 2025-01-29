package it.epicode.eShop.controller;

import it.epicode.eShop.entity.Cart;
import it.epicode.eShop.services.CartItemSvc;
import it.epicode.eShop.services.CartSvc;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartSvc cartSvc;
    private final CartItemSvc cartItemSvc;

    @PostMapping("/add")
    public ResponseEntity<Cart> addProductToCart(
            @RequestParam Long productId,
            @RequestParam int quantity,
            @AuthenticationPrincipal UserDetails user) {

        Cart updatedCart = cartSvc.addProductToCart(productId, quantity, user);
        return new ResponseEntity<>(updatedCart, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Cart> getCartByUser(@AuthenticationPrincipal UserDetails user) {
        return ResponseEntity.ok(cartSvc.getCartByUser(user));
    }

    @PutMapping("/removeCartItem/{idCartItem}")
    public ResponseEntity<Cart> updateCart(@PathVariable Long idCartItem,@AuthenticationPrincipal UserDetails user) {
        Cart cart = cartSvc.deleteCartItemFromCart(idCartItem,user);
        return ResponseEntity.ok(cart);
    }

    @PutMapping("/editQuantity/{idCartItem}")
    public ResponseEntity<String> editQuantity(@PathVariable Long idCartItem, @RequestParam int op) {
        return ResponseEntity.ok(cartItemSvc.editQuantity(idCartItem,op));
    }
}
