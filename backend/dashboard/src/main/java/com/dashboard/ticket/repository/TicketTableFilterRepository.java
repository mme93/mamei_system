package com.dashboard.ticket.repository;

import com.dashboard.ticket.model.entity.TicketTableFilterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketTableFilterRepository extends JpaRepository<TicketTableFilterEntity, Long> {
}
