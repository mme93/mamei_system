package com.dashboard.ticket.service;

import com.dashboard.ticket.model.TicketTableFilterEntity;
import com.dashboard.ticket.repository.TicketTableFilterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketFilterService {

    private final TicketTableFilterRepository ticketTableFilterRepository;

    public TicketFilterService(TicketTableFilterRepository ticketTableFilterRepository) {
        this.ticketTableFilterRepository = ticketTableFilterRepository;
    }

    public List<TicketTableFilterEntity> getAllTicketTableFilter() {
        return ticketTableFilterRepository.findAll();
    }

    public TicketTableFilterEntity createTicketTableFilter() {
        return null;
    }
}
