package com.configmanager.settings.frontend.projects.mamei.service;

import com.configmanager.settings.frontend.projects.mamei.model.dto.MaMeiTicketTableConfigDto;
import com.configmanager.settings.frontend.projects.mamei.model.entity.MaMeiTicketTableConfigEntity;
import com.configmanager.settings.frontend.projects.mamei.repository.MaMeiTicketTableConfigRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MaMeiTicketTableService {

    private final MaMeiTicketTableConfigRepository ticketTableConfigRepository;
    private final MaMeiConfigWrapperService wrapperService;

    public MaMeiTicketTableService(MaMeiTicketTableConfigRepository ticketTableConfigRepository, MaMeiConfigWrapperService wrapperService) {
        this.ticketTableConfigRepository = ticketTableConfigRepository;
        this.wrapperService = wrapperService;
    }

    public MaMeiTicketTableConfigDto loadConfig(String username) {
        return wrapperService.wrapToTicketTableConfigDto(ticketTableConfigRepository.findByOwner(username));
    }

    public Object createConfig(Object config, String userName) {
        ObjectMapper objectMapper = new ObjectMapper();
        MaMeiTicketTableConfigDto dto = objectMapper.convertValue(config, MaMeiTicketTableConfigDto.class);
        dto.setOwner(userName);
        return this.ticketTableConfigRepository.save(wrapperService.wrapToTicketTableConfigEntity(dto));
    }

    public Object updateConfig(Object config) {
        ObjectMapper objectMapper = new ObjectMapper();
        MaMeiTicketTableConfigDto dto = objectMapper.convertValue(config, MaMeiTicketTableConfigDto.class);
        Optional<MaMeiTicketTableConfigEntity> entityOpt = ticketTableConfigRepository.findByOwner(dto.getOwner());
        MaMeiTicketTableConfigEntity entity=entityOpt.get();
        entity.setCurrentTableId(dto.getCurrentTableId());
        entity.setCurrentFilterName(dto.getCurrentFilterName());
        return this.ticketTableConfigRepository.save(entity);
    }

    @Transactional
    public void deleteConfig(String username) {
        this.ticketTableConfigRepository.deleteByOwner(username);
    }
}
