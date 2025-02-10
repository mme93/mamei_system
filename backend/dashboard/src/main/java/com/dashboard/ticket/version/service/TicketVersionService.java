package com.dashboard.ticket.version.service;

import com.dashboard.ticket.model.enums.EProject;
import com.dashboard.ticket.version.model.TicketVersionEntity;
import com.dashboard.ticket.version.repository.TicketVersionRepository;
import org.springframework.stereotype.Service;

@Service
public class TicketVersionService {

    private final TicketVersionRepository ticketVersionRepository;

    public TicketVersionService(TicketVersionRepository ticketVersionRepository) {
        this.ticketVersionRepository = ticketVersionRepository;
    }

    public void increaseVersion(EProject project, Long increaseCount) {
        TicketVersionEntity entity = ticketVersionRepository.findByProject(project)
                .orElseThrow(() -> new RuntimeException("Project not found: " + project));
        entity.setCurrentVersion(increaseCount);
        ticketVersionRepository.save(entity);
    }

    public Long getCurrentVersion(EProject project) {
        return ticketVersionRepository.findByProject(project)
                .orElseThrow(() -> new RuntimeException("Project not found: " + project)).getCurrentVersion();
    }

}
