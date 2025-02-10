package com.dashboard.ticket.version.repository;

import com.dashboard.ticket.model.enums.EProject;
import com.dashboard.ticket.version.model.TicketVersionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketVersionRepository extends JpaRepository<TicketVersionEntity, Long> {

    Optional<TicketVersionEntity> findByProject(EProject project);

}
