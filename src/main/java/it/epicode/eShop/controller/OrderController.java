package it.epicode.eShop.controller;

import it.epicode.eShop.entity.Order;
import it.epicode.eShop.entity.Product;
import it.epicode.eShop.services.OrderSvc;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderSvc orderSvc;

    @PostMapping("/create")
    public ResponseEntity<Order> create(@AuthenticationPrincipal UserDetails user) {
        orderSvc.createOrder(user.getUsername());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Order>> findAll() {
        List<Order> orders = orderSvc.findAll();
        return ResponseEntity.ok(orders);
    }
}
