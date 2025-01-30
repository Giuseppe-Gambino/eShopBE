package it.epicode.eShop.stripe.controller;



import it.epicode.eShop.entity.Order;
import it.epicode.eShop.stripe.dto.ProductRequest;

import it.epicode.eShop.stripe.dto.StripeResponse;
import it.epicode.eShop.stripe.service.StripeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/product/v1")
public class ProductCheckoutController {


    private final StripeService stripeService;


    @PostMapping("/checkout")
    public ResponseEntity<StripeResponse> checkoutProducts(@RequestBody Order productRequest) {
        StripeResponse stripeResponse = stripeService.checkoutProducts(productRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(stripeResponse);
    }
}
