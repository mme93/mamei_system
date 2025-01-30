package com.dashboard.ticket.service;

import com.dashboard.ticket.model.dto.TicketTableFilterCreateDto;
import com.dashboard.ticket.model.dto.TicketTableFilterResponseDto;
import com.dashboard.ticket.model.entity.TicketTableFilterEntity;
import com.dashboard.ticket.model.mapper.TicketFilterMapper;
import com.dashboard.ticket.repository.TicketTableFilterRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TicketFilterService {

    private final TicketTableFilterRepository ticketTableFilterRepository;

    public TicketFilterService(TicketTableFilterRepository ticketTableFilterRepository) {
        this.ticketTableFilterRepository = ticketTableFilterRepository;
    }

    public List<TicketTableFilterResponseDto> getAllTicketTableFilter() throws JsonProcessingException {
        List<TicketTableFilterResponseDto>tableFilterResponseDtos=new ArrayList<>();
        for(TicketTableFilterEntity entity:ticketTableFilterRepository.findAll()){
            tableFilterResponseDtos.add(TicketFilterMapper.entityMapToDto(entity));
        }
        return tableFilterResponseDtos;
    }

    public TicketTableFilterEntity createTicketTableFilter(TicketTableFilterCreateDto filterCreateDto) {
        try {
            TicketTableFilterEntity entity = TicketFilterMapper.dtoMapToEntity(filterCreateDto);
            return ticketTableFilterRepository.save(entity);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public TicketTableFilterEntity updateTicketTableFilter(TicketTableFilterResponseDto filterCreateDto) {
        try {
            TicketTableFilterEntity entity = TicketFilterMapper.dtoMapToEntity(filterCreateDto);
            return ticketTableFilterRepository.save(entity);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<TicketTableFilterEntity> getTicketTableFilterById(Long id) {
        return ticketTableFilterRepository.findById(id);
    }

    public void deleteTicketTableFilterById(Long id) {
        ticketTableFilterRepository.deleteById(id);
    }
}
