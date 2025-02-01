package it.epicode.eShop.repo;

import it.epicode.eShop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByStripeSessionId(String stripeSessionId);

    List<Order> findByAppUser_Username(String username);
}