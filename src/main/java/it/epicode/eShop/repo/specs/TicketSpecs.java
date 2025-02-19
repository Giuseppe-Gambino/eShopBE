package it.epicode.eShop.repo.specs;


import it.epicode.eShop.entity.Ticket;
import it.epicode.eShop.enums.StatusTicket;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class TicketSpecs {
    public static Specification<Ticket> hasTicketStatus(StatusTicket status) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("status"), status);
    }


    public static Specification<Ticket> hasCreatedAt(LocalDate date) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("createdAt"), date);
    }

    public static Specification<Ticket> hasCreatedAtAfterOrEqual(LocalDate startDate) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), startDate);
    }


    public static Specification<Ticket> hasCreatedAtBeforeOrEqual(LocalDate endDate) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("createdAt"), endDate);
    }
}
