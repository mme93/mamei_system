package com.dashboard.ticket.service;

import com.dashboard.ticket.repository.TicketLinksEntityRepository;
import org.springframework.stereotype.Service;

@Service
public class TicketLinksService {

    private final TicketLinksEntityRepository ticketLinksEntityRepository;

    public TicketLinksService(TicketLinksEntityRepository ticketLinksEntityRepository) {
        this.ticketLinksEntityRepository = ticketLinksEntityRepository;
    }


}
