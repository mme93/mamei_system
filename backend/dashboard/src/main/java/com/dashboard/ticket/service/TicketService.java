package com.dashboard.ticket.service;

import com.dashboard.ticket.model.CreateTicketDto;
import com.dashboard.ticket.model.TicketEntity;
import com.dashboard.ticket.model.mapper.TicketMapper;
import com.dashboard.ticket.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public TicketEntity createTicket(CreateTicketDto ticket){
       return ticketRepository.save(TicketMapper.createTicketMapToTicket(ticket));
    }

    public Optional<TicketEntity> getTicketById(Long id) {
      return ticketRepository.findById(id);
    }

    public List<TicketEntity> getAllTickets() {
        return ticketRepository.findAll();
    }

    public void deleteTicketById(Long id) {
        ticketRepository.deleteById(id);
    }
}
