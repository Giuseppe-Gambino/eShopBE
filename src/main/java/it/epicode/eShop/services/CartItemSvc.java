package it.epicode.eShop.services;

import it.epicode.eShop.entity.CartItem;
import it.epicode.eShop.repo.CartItemRepository;
import it.epicode.eShop.repo.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CartItemSvc {
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;


    public void addToCart(Long idProduct ) {

    }
}
