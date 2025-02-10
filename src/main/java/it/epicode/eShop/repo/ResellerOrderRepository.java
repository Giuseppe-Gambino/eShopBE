package it.epicode.eShop.repo;

import it.epicode.eShop.entity.ResellerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ResellerOrderRepository extends JpaRepository<ResellerOrder, Long>, JpaSpecificationExecutor<ResellerOrder> {
}