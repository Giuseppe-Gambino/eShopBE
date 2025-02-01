package it.epicode.eShop.services;

import it.epicode.eShop.auth.AppUser;
import it.epicode.eShop.auth.AppUserRepository;
import it.epicode.eShop.auth.AppUserService;
import it.epicode.eShop.entity.Cart;
import it.epicode.eShop.entity.CartItem;
import it.epicode.eShop.entity.Order;
import it.epicode.eShop.entity.OrderItem;
import it.epicode.eShop.enums.StatusOrder;
import it.epicode.eShop.repo.CartItemRepository;
import it.epicode.eShop.repo.CartRepository;
import it.epicode.eShop.repo.OrderItemRepository;
import it.epicode.eShop.repo.OrderRepository;
import it.epicode.eShop.stripe.dto.StripeResponse;
import it.epicode.eShop.stripe.service.StripeService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderSvc {
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderItemRepository orderItemRepository;
    private final AppUserRepository appUserRepository;
    private final StripeService stripeService;

    @Transactional
    public StripeResponse createOrder(String username) {
        AppUser appUser = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Utente con username: " + username + "non trovato"));

        Cart cart = cartRepository.findByAppUser_Username(username)
                .orElseThrow(() -> new EntityNotFoundException("Cart con Utente username: " + username + "non trovato"));


        if (cart.getCartItems().isEmpty()) {
            throw new IllegalStateException("Il carrello è vuoto!");
        }

        List<CartItem> cartItemList = cart.getCartItems();

        Order order = new Order();
        order.setAppUser(appUser);
        order.setCreatedAt(LocalDate.now());
        order.setStatus(StatusOrder.PENDING);

        orderRepository.save(order);

        List<OrderItem> orderItemList = new ArrayList<>();
        for(CartItem cartItem : cartItemList) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(cartItem.getProduct());

            orderItem.setPrice(cartItem.getProduct().getPrice());

            orderItem.setQuantity(cartItem.getQuantity());

            orderItemList.add(orderItem);

//            aggiungo gli orderItem nell'order
            order.getOrderItems().add(orderItem);
        }


        BigDecimal totalOrderPrice = orderItemList.stream()
                .map(OrderItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add); // Somma tutti i prezzi
        order.setTotal(totalOrderPrice);

        orderItemRepository.saveAll(orderItemList);

//      elimino i cartitem dal database
        cartItemRepository.deleteAll(cart.getCartItems());

        cart.getCartItems().clear();
        cartRepository.save(cart);

        orderRepository.save(order);


        return stripeService.checkoutProducts(order);
    }

    public void updateOrderPaymentStatus(String stripeSessionId, StatusOrder status) {
        Order order = orderRepository.findByStripeSessionId(stripeSessionId);
        if (order != null) {
            order.setStatus(status);
            orderRepository.save(order);
        }
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public List<Order> getOrderByUser(String username) {
        return orderRepository.findByAppUser_Username(username);
    }
}
