package it.epicode.eShop.repo.specs;

import it.epicode.eShop.entity.ResellerOrder;
import it.epicode.eShop.enums.StatusResellerOrder;
import org.springframework.data.jpa.domain.Specification;


import java.time.LocalDate;


public class ResellerOrderSpecs {


    public static Specification<ResellerOrder> hasResellerUsername(String username) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("reseller").get("username"), username);
    }


    public static Specification<ResellerOrder> hasOrderStatus(StatusResellerOrder statusResellerOrder) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("orderStatus"), statusResellerOrder);
    }


    public static Specification<ResellerOrder> hasCreatedAt(LocalDate date) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("createdAt"), date);
    }

    public static Specification<ResellerOrder> hasCreatedAtAfterOrEqual(LocalDate startDate) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), startDate);
    }


    public static Specification<ResellerOrder> hasCreatedAtBeforeOrEqual(LocalDate endDate) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("createdAt"), endDate);
    }
}
