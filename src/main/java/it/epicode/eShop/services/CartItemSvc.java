package it.epicode.eShop.services;

import it.epicode.eShop.entity.CartItem;
import it.epicode.eShop.repo.CartItemRepository;
import it.epicode.eShop.repo.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CartItemSvc {
    private final CartItemRepository cartItemRepository;


    public String editQuantity(Long idCartItem, int op) {
        CartItem cartItem = cartItemRepository.findById(idCartItem)
                .orElseThrow(() -> new EntityNotFoundException("CartItem con id:" + idCartItem + "non trovato"));

        if(op == 1) {
        cartItem.setQuantity(cartItem.getQuantity() + 1);
        } else if (op == 0 && cartItem.getQuantity() > 1) {
            cartItem.setQuantity(cartItem.getQuantity() - 1);
        } else if (op == 0 && cartItem.getQuantity() == 1) {
            return "quantita gia al minimo";
        }

        cartItemRepository.save(cartItem);
        return "modifica effetuata";
    }


}
