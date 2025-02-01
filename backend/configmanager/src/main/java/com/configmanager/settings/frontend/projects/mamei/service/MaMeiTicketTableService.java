package com.configmanager.settings.frontend.projects.mamei.service;

import com.configmanager.settings.frontend.projects.mamei.model.dto.MaMeiTicketTableConfigDto;
import com.configmanager.settings.frontend.projects.mamei.repository.MaMeiTicketTableConfigRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class MaMeiTicketTableService {

    private final MaMeiTicketTableConfigRepository ticketTableConfigRepository;
    private final MaMeiConfigWrapperService wrapperService;

    public MaMeiTicketTableService(MaMeiTicketTableConfigRepository ticketTableConfigRepository, MaMeiConfigWrapperService wrapperService) {
        this.ticketTableConfigRepository = ticketTableConfigRepository;
        this.wrapperService = wrapperService;
    }

    public MaMeiTicketTableConfigDto loadConfig(String username) {

        return null;
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
        return this.ticketTableConfigRepository.save(wrapperService.wrapToTicketTableConfigEntity(dto));
    }

    @Transactional
    public void deleteConfig(String username) {
        this.ticketTableConfigRepository.deleteByOwner(username);
    }
}
