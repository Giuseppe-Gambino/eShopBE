package it.epicode.eShop.repo;

import it.epicode.eShop.entity.ResellerOrder;
import it.epicode.eShop.enums.StatusResellerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDate;

public interface ResellerOrderRepository extends JpaRepository<ResellerOrder, Long>, JpaSpecificationExecutor<ResellerOrder> {
    int countByReseller_IdAndCreatedAt(Long resellerId, LocalDate today);

    int countByReseller_IdAndCreatedAtAndOrderStatus(Long resellerId, LocalDate today, StatusResellerOrder status);
}