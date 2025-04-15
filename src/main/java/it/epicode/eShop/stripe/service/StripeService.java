package it.epicode.eShop.stripe.service;


import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;

import com.stripe.param.checkout.SessionCreateParams;
import it.epicode.eShop.entity.Order;
import it.epicode.eShop.entity.OrderItem;
import it.epicode.eShop.stripe.dto.ProductRequest;
import it.epicode.eShop.stripe.dto.StripeResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class StripeService {

    @Value("${stripe.secretKey}")
    private String secretKey;

    //stripe -API
    //-> productName , amount , quantity , currency
    //-> return sessionId and url

    @Value("{url.front}")
    private String urlFrontEnd;



    public StripeResponse checkoutProducts(Order order) {
            // Set your secret key. Remember to switch to your live secret key in production!
            Stripe.apiKey = secretKey;

        List<SessionCreateParams.LineItem> lineItems = new ArrayList<>();


            for (OrderItem orderItem: order.getOrderItems()) {

                // Creazione dati prodotto
                SessionCreateParams.LineItem.PriceData.ProductData productData =
                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                .setName(orderItem.getProduct().getName())
                                .build();

                BigDecimal priceInEuros = orderItem.getPrice();
                BigDecimal priceInCents = priceInEuros.multiply(new BigDecimal("100"))
                        .setScale(0, RoundingMode.HALF_UP);

                // Creazione dati prezzo
                SessionCreateParams.LineItem.PriceData priceData =
                        SessionCreateParams.LineItem.PriceData.builder()
                                .setCurrency("EUR")
                                .setUnitAmount(priceInCents.longValueExact())
                                .setProductData(productData)
                                .build();

                // Creazione LineItem
                SessionCreateParams.LineItem lineItem =
                        SessionCreateParams
                                .LineItem.builder()
                                .setQuantity(orderItem.getQuantity().longValue())
                                .setPriceData(priceData)
                                .build();

                lineItems.add(lineItem);
            }


//            Creazione session con la lista degli items
            SessionCreateParams params =
                    SessionCreateParams.builder()
                            .setMode(SessionCreateParams.Mode.PAYMENT)
                            .setSuccessUrl( urlFrontEnd + "/cart/success") // link della pagina di successo
                            .setCancelUrl( urlFrontEnd + "/cart") // link della pagina di fallimento
                            .addAllLineItem(lineItems)
                            .build();

            // Create new session
            Session session = null;
            try {
                session = Session.create(params);
            } catch (StripeException e) {
                //log the error
            }

            order.setStripeSessionId(session.getId());

            return StripeResponse
                    .builder()
                    .status("SUCCESS")
                    .message("Payment session created ")
                    .sessionId(session.getId())
                    .sessionUrl(session.getUrl())
                    .build();
        }

}
