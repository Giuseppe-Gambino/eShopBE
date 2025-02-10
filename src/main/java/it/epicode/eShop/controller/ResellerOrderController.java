package it.epicode.eShop.controller;

import it.epicode.eShop.dto.ProductDTO;
import it.epicode.eShop.entity.Product;
import it.epicode.eShop.entity.ResellerOrder;
import it.epicode.eShop.enums.StatusResellerOrder;
import it.epicode.eShop.services.ResellerOrderSvc;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RequiredArgsConstructor
@RestController
@RequestMapping("/resellerOrder")
public class ResellerOrderController {
    private final ResellerOrderSvc resellerOrderSvc;

    @PutMapping("/{id}")
    public ResponseEntity<ResellerOrder> update(@PathVariable Long id, @RequestParam StatusResellerOrder statusResellerOrder) {
        return ResponseEntity.ok(resellerOrderSvc.updateResellerOrder(id,statusResellerOrder));
    }



        @GetMapping
        public ResponseEntity<Page<ResellerOrder>> getFilteredOrders(
                @AuthenticationPrincipal UserDetails user,
                @RequestParam(required = false) StatusResellerOrder orderStatus,

                @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,

                @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                Pageable pageable) {

            // Estrai lo username dall'utente autenticato
            String username = user.getUsername();

            Page<ResellerOrder> orders = resellerOrderSvc.getFilteredResellerOrder(
                    username, orderStatus, date, startDate, endDate, pageable);
            return ResponseEntity.ok(orders);
        }


}
