package com.dashboard.ticket.repository;

import com.dashboard.ticket.model.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<TicketEntity, Long> {
}
