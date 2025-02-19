package it.epicode.eShop.controller;

import it.epicode.eShop.dto.TicketDTO;
import it.epicode.eShop.dto.TicketSummaryDTO;
import it.epicode.eShop.entity.ResellerOrder;
import it.epicode.eShop.entity.Ticket;
import it.epicode.eShop.enums.StatusResellerOrder;
import it.epicode.eShop.enums.StatusTicket;

import it.epicode.eShop.services.TicketSvc;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/Ticket")
public class TicketController {
    private final TicketSvc ticketSvc;

    @PostMapping
    public ResponseEntity<Ticket> create(@Valid @RequestBody TicketDTO ticketDTO, @AuthenticationPrincipal UserDetails user) {
        return new ResponseEntity<>(ticketSvc.create(ticketDTO,user.getUsername()), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Ticket>> findAll() {
        return ResponseEntity.ok(ticketSvc.findAll());
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Page<Ticket>> findPageTicket(Pageable pageable) {
        return ResponseEntity.ok(ticketSvc.findPageTicket(pageable));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> delete(@PathVariable Long id) {
       ticketSvc.delete(id);
        return new ResponseEntity<>("Ticket eliminato correttamente!",HttpStatus.NO_CONTENT);
    }

    @PutMapping("/status/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Ticket> editStatus(@PathVariable Long id, @RequestParam StatusTicket statusTicket) {
        return ResponseEntity.ok(ticketSvc.statusTicket(id,statusTicket));
    }

    @GetMapping("/findByUsername")
    public ResponseEntity<Page<Ticket>> findByUsername(@RequestParam String username,Pageable pageable) {
        return ResponseEntity.ok(ticketSvc.findByUsername(username,pageable));
    }

    @GetMapping("/stats")
    public ResponseEntity<TicketSummaryDTO> stats() {
        return ResponseEntity.ok(ticketSvc.stats());
    }

    @GetMapping("/filtered")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Page<Ticket>> getFilteredTicket(
            @RequestParam(required = false) StatusTicket status,

            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,

            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            Pageable pageable) {


        Page<Ticket> ticket = ticketSvc.getFilteredTicket(
          status, date, startDate, endDate, pageable);
        return ResponseEntity.ok(ticket);
    }
}
