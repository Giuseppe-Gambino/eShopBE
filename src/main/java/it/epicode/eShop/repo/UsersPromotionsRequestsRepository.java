package it.epicode.eShop.repo;

import it.epicode.eShop.entity.Cart;
import it.epicode.eShop.entity.UsersPromotionsRequests;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsersPromotionsRequestsRepository extends JpaRepository<UsersPromotionsRequests, Long> {
    Optional<List<UsersPromotionsRequests>> findByAppUser_Username(String username);
}