package it.epicode.eShop.services;

import it.epicode.eShop.entity.Order;
import it.epicode.eShop.entity.OrderItem;
import it.epicode.eShop.entity.Product;
import it.epicode.eShop.entity.ResellerOrder;
import it.epicode.eShop.enums.StatusResellerOrder;
import it.epicode.eShop.repo.ResellerOrderRepository;
import it.epicode.eShop.repo.specs.ProductSpecs;
import it.epicode.eShop.repo.specs.ResellerOrderSpecs;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ResellerOrderSvc {
    private final ResellerOrderRepository resellerOrderRepo;


    public List<ResellerOrder> createResellerOrder(Order order) {
        List<OrderItem> orderItemList = order.getOrderItems();

        List<ResellerOrder> resellerOrderList = new ArrayList<>();

        for (OrderItem item : orderItemList) {
            ResellerOrder resellerOrder = new ResellerOrder();
            resellerOrder.setOrderItem(item);
            resellerOrder.setReseller(item.getProduct().getReseller());
            resellerOrder.setCreatedAt(LocalDate.now());
            resellerOrder.setOrderStatus(StatusResellerOrder.ORDINE_RICEVUTO);

            resellerOrderList.add(resellerOrder);
        }

        return resellerOrderRepo.saveAll(resellerOrderList);

    }

    public ResellerOrder updateResellerOrder(Long id, StatusResellerOrder statusResellerOrder) {

       ResellerOrder resellerOrder = resellerOrderRepo.findById(id)
               .orElseThrow(() -> new EntityNotFoundException("ResellerOrder con id: " + id + "non trovato"));

       resellerOrder.setOrderStatus(statusResellerOrder);

       return resellerOrderRepo.save(resellerOrder);

    }

    public Page<ResellerOrder> getFilteredResellerOrder(
            String username,
            StatusResellerOrder orderStatus,
            LocalDate date,
            LocalDate startDate,
            LocalDate endDate,
            Pageable pageable) {

        // Filtriamo sempre per l'utente autenticato
        Specification<ResellerOrder> spec = Specification.where(ResellerOrderSpecs.hasResellerUsername(username));

        if (orderStatus != null) {
            spec = spec.and(ResellerOrderSpecs.hasOrderStatus(orderStatus));
        }

        // Se è specificata una data esatta, la usiamo e ignoriamo startDate/endDate
        if (date != null) {
            spec = spec.and(ResellerOrderSpecs.hasCreatedAt(date));
        } else {
            // Se non è fornita una data esatta, usiamo il range se presente
            if (startDate != null && endDate != null) {
                spec = spec.and(ResellerOrderSpecs.hasCreatedAtAfterOrEqual(startDate))
                        .and(ResellerOrderSpecs.hasCreatedAtBeforeOrEqual(endDate));
            } else if (startDate != null) {
                spec = spec.and(ResellerOrderSpecs.hasCreatedAtAfterOrEqual(startDate));
            } else if (endDate != null) {
                spec = spec.and(ResellerOrderSpecs.hasCreatedAtBeforeOrEqual(endDate));
            }
        }

        return resellerOrderRepo.findAll(spec, pageable);
    }



}
