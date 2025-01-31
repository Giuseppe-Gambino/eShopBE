package it.epicode.eShop.stripe.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.model.EventDataObjectDeserializer;
import com.stripe.model.StripeObject;
import com.stripe.model.billingportal.Session;
import com.stripe.net.Webhook;
import com.stripe.service.OrderService;
import it.epicode.eShop.enums.StatusOrder;
import it.epicode.eShop.services.OrderSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/webhook")
public class StripeWebhookController {

    @Value("${stripe.webhook.secret}")
    private String endpointSecret; // Da configurare nelle ENV

    @Autowired
    private OrderSvc orderSvc;


    @PostMapping("/stripe")
    public ResponseEntity<String> handleStripeWebhook(@RequestBody String payload, @RequestHeader("Stripe-Signature") String sigHeader) {
        try {
            // Verifica la firma del webhook
            Event event = Webhook.constructEvent(payload, sigHeader, endpointSecret);
            System.out.println("Ricevuto evento: " + event.getType());

            // Usa Jackson per leggere il JSON
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(payload);

            // Estrarre il sessionId manualmente
            String stripeSessionId = jsonNode.get("data").get("object").get("id").asText();
            System.out.println("✅ Stripe Session ID trovato: " + stripeSessionId);

            if ("checkout.session.completed".equals(event.getType())) {
                orderSvc.updateOrderPaymentStatus(stripeSessionId, StatusOrder.PAID);
            } else if ("checkout.session.expired".equals(event.getType()) || "payment_intent.payment_failed".equals(event.getType())) {
                orderSvc.updateOrderPaymentStatus(stripeSessionId, StatusOrder.FAILED);
            }

            return ResponseEntity.ok("Webhook ricevuto");
        } catch (SignatureVerificationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid signature");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore interno");
        }
    }

}
