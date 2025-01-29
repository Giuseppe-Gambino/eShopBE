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

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CartSvc {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;

    public Cart getCartByUser(UserDetails user) {
        Cart cart = cartRepository.findByAppUser_Username(user.getUsername())
                .orElseThrow(() -> new EntityNotFoundException("Utente con username:" + user.getUsername() + "non trovato"));
        return cart;
    }

    public Cart create(AppUser appUser) {
        Cart cart = new Cart();
        cart.setAppUser(appUser);
        return cartRepository.save(cart);
    }

    public Cart addProductToCart(Long productId, int quantity, UserDetails user) {

        Cart cart = cartRepository.findByAppUser_Username(user.getUsername())
                .orElseThrow(() -> new EntityNotFoundException("Utente con username:" + user.getUsername() + "non trovato"));


        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Prodotto con id:" +  productId + "non trovato"));


        Optional<CartItem> existingCartItem = cart.getCartItems()
                .stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();

        if (existingCartItem.isPresent()) {

            CartItem cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            cartItemRepository.save(cartItem);
        } else {

            CartItem newCartItem = new CartItem();
            newCartItem.setCart(cart);
            newCartItem.setProduct(product);
            newCartItem.setQuantity(quantity);
            cartItemRepository.save(newCartItem);


            cart.getCartItems().add(newCartItem);
        }

        return cartRepository.save(cart);
    }

    public Cart deleteCartItemFromCart(Long idCartItem, UserDetails user) {
        Cart cart = cartRepository.findByAppUser_Username(user.getUsername())
                .orElseThrow(() -> new EntityNotFoundException("Utente con username: " + user.getUsername() + " non trovato"));


        List<CartItem> cartItemList = cart.getCartItems();


        Optional<CartItem> existingCartItem = cartItemList.stream()
                .filter(item -> item.getId().equals(idCartItem))
                .findFirst();


        if (existingCartItem.isPresent()) {

            cartItemList.remove(existingCartItem.get());


            cartItemRepository.delete(existingCartItem.get()); // Assicurati di avere un repository per CartItem
        } else {
            throw new EntityNotFoundException("CartItem con id: " + idCartItem + " non trovato nel carrello.");
        }


        cart.setCartItems(cartItemList);

        cartRepository.save(cart);

        return cart;
    }


}
