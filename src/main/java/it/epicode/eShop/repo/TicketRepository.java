package it.epicode.eShop.repo;

import it.epicode.eShop.entity.Ticket;
import it.epicode.eShop.enums.StatusTicket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>, JpaSpecificationExecutor<Ticket> {
    Optional<Page<Ticket>> findByAppUser_Username(String username,Pageable pageable);

    @Query("SELECT t FROM Ticket t")
    Page<Ticket> findPageTicket(Pageable pageable);

    int countByCreatedAt(LocalDate today);

    int countByCreatedAtAndStatus(LocalDate createdAt, StatusTicket status);

}