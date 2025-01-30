package com.dashboard.ticket.repository;

import com.dashboard.ticket.model.entity.TicketLinksEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketLinksEntityRepository extends JpaRepository<TicketLinksEntity, Long> {
}
