package it.epicode.eShop.stripe.service;


import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;

import com.stripe.param.checkout.SessionCreateParams;
import it.epicode.eShop.entity.Order;
import it.epicode.eShop.stripe.dto.ProductRequest;
import it.epicode.eShop.stripe.dto.StripeResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class StripeService {

    @Value("${stripe.secretKey}")
    private String secretKey;

    //stripe -API
    //-> productName , amount , quantity , currency
    //-> return sessionId and url

    BigDecimal valore = new BigDecimal("200.00");
    Long amount = valore.longValueExact() * 100;



    public StripeResponse checkoutProducts(Order order) {
            // Set your secret key. Remember to switch to your live secret key in production!
            Stripe.apiKey = secretKey;

            // Create a PaymentIntent with the order amount and currency
            SessionCreateParams.LineItem.PriceData.ProductData productData =
                    SessionCreateParams.LineItem.PriceData.ProductData.builder()
                            .setName("Order N:" + order.getId())
                            .build();

            // Create new line item with the above product data and associated price
            SessionCreateParams.LineItem.PriceData priceData =
                    SessionCreateParams.LineItem.PriceData.builder()
                            .setCurrency("EUR")
                            .setUnitAmount(order.getTotal().longValueExact()*100)
                            .setProductData(productData)
                            .build();

            // Create new line item with the above price data
            SessionCreateParams.LineItem lineItem =
                    SessionCreateParams
                            .LineItem.builder()
                            .setQuantity(1L)
                            .setPriceData(priceData)
                            .build();

            // Create new session with the line items
            SessionCreateParams params =
                    SessionCreateParams.builder()
                            .setMode(SessionCreateParams.Mode.PAYMENT)
                            .setSuccessUrl("https://www.youtube.com/watch?v=BczS-wbxgp4") // link della pagina di successo
                            .setCancelUrl("http://localhost:8080/cancel") // link della pagina di fallimento
                            .addLineItem(lineItem)
                            .build();

            // Create new session
            Session session = null;
            try {
                session = Session.create(params);
            } catch (StripeException e) {
                //log the error
            }

            return StripeResponse
                    .builder()
                    .status("SUCCESS")
                    .message("Payment session created ")
                    .sessionId(session.getId())
                    .sessionUrl(session.getUrl())
                    .build();
        }

}
