package it.epicode.eShop.services;

import it.epicode.eShop.auth.AppUser;
import it.epicode.eShop.auth.AppUserService;
import it.epicode.eShop.dto.TicketDTO;
import it.epicode.eShop.dto.TicketSummaryDTO;
import it.epicode.eShop.entity.ResellerOrder;
import it.epicode.eShop.entity.Ticket;
import it.epicode.eShop.enums.StatusResellerOrder;
import it.epicode.eShop.enums.StatusTicket;
import it.epicode.eShop.repo.TicketRepository;
import it.epicode.eShop.repo.specs.ResellerOrderSpecs;
import it.epicode.eShop.repo.specs.TicketSpecs;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TicketSvc {
    private final TicketRepository ticketRepository;
    private final AppUserService appUserService;

    public Ticket create(TicketDTO ticketDTO, String username) {

        AppUser appUser = appUserService.findByUsername(username)
                .orElseThrow(()->new EntityNotFoundException("Utente con username: " + username + "non trovato"));

        Ticket ticket = new Ticket();
        BeanUtils.copyProperties(ticketDTO, ticket);
        ticket.setAppUser(appUser);
        ticket.setCreatedAt(LocalDate.now());
        ticket.setStatus(StatusTicket.RICEVUTO);

        return ticketRepository.save(ticket);
    }

    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    public Page<Ticket> findPageTicket(Pageable pageable) {
        return ticketRepository.findPageTicket(pageable);
    }

    public Page<Ticket> findByUsername(String username, Pageable pageable) {
        Page<Ticket> ticketPage = ticketRepository.findByAppUser_Username(username, pageable)
                .orElseThrow(()->new EntityNotFoundException("non esistono richieste dall'utente:" + username));
        return ticketPage;
    }

    public void delete(Long id) {
       Ticket ticket = ticketRepository.findById(id)
               .orElseThrow(()->new EntityNotFoundException("Richiesta con id:" + id + "non trovata"));

       ticketRepository.delete(ticket);
    }

    public Ticket statusTicket(Long id, StatusTicket statusTicket) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Richiesta con id:" + id + "non trovata"));

        ticket.setStatus(statusTicket);

        return ticketRepository.save(ticket);
    }

    public TicketSummaryDTO stats() {

        LocalDate today = LocalDate.now();

        TicketSummaryDTO ticketSummaryDTO = new TicketSummaryDTO();
        ticketSummaryDTO.setTotalTickets(ticketRepository.countByCreatedAt(today));
        ticketSummaryDTO.setInEsecuzione(ticketRepository.countByCreatedAtAndStatus(today, StatusTicket.IN_ESECUZIONE));
        ticketSummaryDTO.setCompletati(ticketRepository.countByCreatedAtAndStatus(today, StatusTicket.COMPLETATO));
        ticketSummaryDTO.setCancellati(ticketRepository.countByCreatedAtAndStatus(today, StatusTicket.CANCELLATO));


        return ticketSummaryDTO;
    }

    public Page<Ticket> getFilteredTicket(
            StatusTicket status,
            LocalDate date,
            LocalDate startDate,
            LocalDate endDate,
            Pageable pageable) {


        Specification<Ticket> spec = Specification.where(null);

        if (status != null) {
            spec = spec.and(TicketSpecs.hasTicketStatus(status));
        }


        if (date != null) {
            spec = spec.and(TicketSpecs.hasCreatedAt(date));
        } else {

            if (startDate != null && endDate != null) {
                spec = spec.and(TicketSpecs.hasCreatedAtAfterOrEqual(startDate))
                        .and(TicketSpecs.hasCreatedAtBeforeOrEqual(endDate));
            } else if (startDate != null) {
                spec = spec.and(TicketSpecs.hasCreatedAtAfterOrEqual(startDate));
            } else if (endDate != null) {
                spec = spec.and(TicketSpecs.hasCreatedAtBeforeOrEqual(endDate));
            }
        }

        return ticketRepository.findAll(spec, pageable);
    }
}
