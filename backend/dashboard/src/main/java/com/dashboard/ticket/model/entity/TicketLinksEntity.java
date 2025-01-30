package com.dashboard.ticket.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ticket_links")
public class TicketLinksEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private Long ticketId;

    @Column(length = 2000)
    private String linkedTicketIds;

    public TicketLinksEntity(Long ticketId, String linkedTicketIds) {
        this.ticketId = ticketId;
        this.linkedTicketIds = linkedTicketIds;
    }
}
