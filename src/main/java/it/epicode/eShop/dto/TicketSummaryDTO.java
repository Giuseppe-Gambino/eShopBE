package it.epicode.eShop.dto;

import lombok.Data;

@Data
public class TicketSummaryDTO {
    private int totalTickets;
    private int InEsecuzione;
    private int Completati;
    private int Cancellati;
}
