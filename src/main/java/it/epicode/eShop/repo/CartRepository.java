package it.epicode.eShop.repo;

import it.epicode.eShop.auth.AppUser;
import it.epicode.eShop.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByAppUser_Username(String username);

}
