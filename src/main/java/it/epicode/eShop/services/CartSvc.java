package it.epicode.eShop.services;

import it.epicode.eShop.auth.AppUser;

import it.epicode.eShop.entity.Cart;
import it.epicode.eShop.entity.CartItem;
import it.epicode.eShop.entity.Product;
import it.epicode.eShop.repo.CartItemRepository;
import it.epicode.eShop.repo.CartRepository;
import it.epicode.eShop.repo.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CartSvc {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;


    public Cart create(AppUser appUser) {
        Cart cart = new Cart();
        cart.setAppUser(appUser);
        return cartRepository.save(cart);
    }

    public Cart addProductToCart(Long productId, int quantity, UserDetails user) {
        // 1. Recupera il carrello dell'utente
        Cart cart = cartRepository.findByAppUser_Username(user.getUsername())
                .orElseThrow(() -> new EntityNotFoundException("Utente con username:" + user.getUsername() + "non trovato"));

        // 2. Recupera il prodotto
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Prodotto con id:" +  productId + "non trovato"));

        // 3. Verifica se il prodotto è già nel carrello
        Optional<CartItem> existingCartItem = cart.getCartItems()
                .stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();

        if (existingCartItem.isPresent()) {
            // 3a. Incrementa la quantità
            CartItem cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            cartItemRepository.save(cartItem);
        } else {
            // 3b. Crea un nuovo CartItem
            CartItem newCartItem = new CartItem();
            newCartItem.setCart(cart);
            newCartItem.setProduct(product);
            newCartItem.setQuantity(quantity);
            cartItemRepository.save(newCartItem);

            // Aggiungi il nuovo CartItem al carrello
            cart.getCartItems().add(newCartItem);
        }

        // 4. Salva il carrello aggiornato
        return cartRepository.save(cart);
    }

}
