package com.dashboard.ticket.service;

import com.dashboard.ticket.model.CreateTicketDto;
import com.dashboard.ticket.model.ETicketStatus;
import com.dashboard.ticket.model.TicketEntity;
import com.dashboard.ticket.model.mapper.TicketMapper;
import com.dashboard.ticket.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    private final TicketValidatorService validatorService;
    private final TicketRepository ticketRepository;

    public TicketService(TicketValidatorService validatorService, TicketRepository ticketRepository) {
        this.validatorService = validatorService;
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

    public TicketEntity updateTicketStatus(ETicketStatus status, Long id) {
        Optional<TicketEntity>ticketOpt= ticketRepository.findById(id);
        if(validatorService.isValidStatusChange(ticketOpt,status)){
            TicketEntity ticket = ticketOpt.get();
            ticket.setStatus(status);
            ticket = ticketRepository.save(ticket);
            return ticket;
        }
       throw new IllegalArgumentException(String.format("Can´t find ticket by ID %s or status change is invalid.",id));
    }

    public TicketEntity updateTicket(TicketEntity ticket) {
        Optional<TicketEntity>ticketOpt= ticketRepository.findById(ticket.getId());
        if(ticketOpt.isPresent()){
            TicketEntity ticketEntity = ticketOpt.get();
            ticketEntity.setTitle(ticket.getTitle());
            ticketEntity.setType(ticket.getType());
            ticketEntity.setLabel(ticket.getLabel());
            ticketEntity.setClassification(ticket.getClassification());
            ticketEntity.setStatus(ticket.getStatus());
            ticketEntity.setDescription(ticket.getDescription());
            ticketEntity.setStartDate(ticket.getStartDate());
            ticketEntity.setEndDate(ticket.getEndDate());
            ticketEntity.setDeadLine(ticket.isDeadLine());
            ticketRepository.save(ticketEntity);
            return ticketEntity;
        }
        throw new IllegalArgumentException("Problem to update ticket");
    }
}
