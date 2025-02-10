package com.dashboard.ticket.service;

import com.dashboard.ticket.TicketIdGenerator;
import com.dashboard.ticket.model.dto.CreateTicketDto;
import com.dashboard.ticket.model.dto.TicketDto;
import com.dashboard.ticket.model.enums.ETicketStatus;
import com.dashboard.ticket.model.entity.TicketEntity;
import com.dashboard.ticket.model.mapper.TicketMapper;
import com.dashboard.ticket.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    private final TicketValidatorService validatorService;
    private final TicketRepository ticketRepository;
    private final TicketIdGenerator ticketIdGenerator;

    public TicketService(TicketValidatorService validatorService, TicketRepository ticketRepository, TicketIdGenerator ticketIdGenerator) {
        this.validatorService = validatorService;
        this.ticketRepository = ticketRepository;
        this.ticketIdGenerator = ticketIdGenerator;
    }

    public TicketEntity createTicket(CreateTicketDto ticket) {
        TicketEntity ticketEntity = ticketRepository.save(TicketMapper.createTicketMapToTicket(ticket));
        String projectId = ticketIdGenerator.generateId(ticketEntity.getId(), ticketEntity.getProject());
        ticketEntity.setProjectId(projectId);
        return ticketRepository.save(ticketEntity);
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

    public TicketDto updateTicketStatus(ETicketStatus status, Long id) {
        Optional<TicketEntity> ticketOpt = ticketRepository.findById(id);
        if (validatorService.isValidStatusChange(ticketOpt, status)) {
            TicketEntity ticket = ticketOpt.get();
            ticket.setStatus(status);
            ticket = ticketRepository.save(ticket);
            return TicketMapper.mapToDto(ticket);
        }
        throw new IllegalArgumentException(String.format("Can´t find ticket by ID %s or status change is invalid.", id));
    }

    public TicketDto updateTicket(TicketEntity ticket) {
        Optional<TicketEntity> ticketOpt = ticketRepository.findById(ticket.getId());
        if (ticketOpt.isPresent()) {
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
            return TicketMapper.mapToDto(ticketEntity);
        }
        throw new IllegalArgumentException("Problem to update ticket");
    }

}
