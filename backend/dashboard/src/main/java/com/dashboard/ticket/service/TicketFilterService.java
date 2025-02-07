package com.dashboard.ticket.service;

import com.dashboard.ticket.model.dto.MaMeiTicketTableConfigDto;
import com.dashboard.ticket.model.dto.TicketTableFilterCreateDto;
import com.dashboard.ticket.model.dto.TicketTableFilterResponseDto;
import com.dashboard.ticket.model.entity.TicketTableFilterEntity;
import com.dashboard.ticket.model.mapper.TicketFilterMapper;
import com.dashboard.ticket.repository.TicketTableFilterRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketFilterService {

    private final TicketTableFilterRepository ticketTableFilterRepository;
    private final DiscoveryClientService discoveryClientService;
    private final WebClient.Builder webClient;

    public List<TicketTableFilterResponseDto> getAllTicketTableFilter(String userName) throws JsonProcessingException {
        MaMeiTicketTableConfigDto dto = loadMaMeiTicketTableConfigs(userName);
        List<TicketTableFilterResponseDto> tableFilterResponseDtos = new ArrayList<>();
        for (TicketTableFilterEntity entity : ticketTableFilterRepository.findAll()) {
            if (dto.getTableFilterIds().contains(entity.getId())) {
                tableFilterResponseDtos.add(TicketFilterMapper.entityMapToDto(entity));
            }
        }
        return tableFilterResponseDtos;
    }

    public TicketTableFilterResponseDto getCurrentTicketTableFilter(String userName) throws JsonProcessingException {
        MaMeiTicketTableConfigDto dto = loadMaMeiTicketTableConfigs(userName);
        Optional<TicketTableFilterEntity> entity = ticketTableFilterRepository.findById(dto.getCurrentTableId());
        if (entity.isPresent()) {
            return TicketFilterMapper.entityMapToDto(entity.get());
        }
        throw new NotFoundException(String.format("No filter found by userName:%s", userName));
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

    private MaMeiTicketTableConfigDto loadMaMeiTicketTableConfigs(String userName) {
        String uri = String.format("%s/configmanager/frontend/mamei/TICKET_TABLE",
                discoveryClientService.getConfigManagerByName());
        return webClient
                .build()
                .get()
                .uri(uri)
                .headers(headers -> headers.set("X-Username", userName))
                .retrieve()
                .bodyToMono(MaMeiTicketTableConfigDto.class).block();
    }

}
