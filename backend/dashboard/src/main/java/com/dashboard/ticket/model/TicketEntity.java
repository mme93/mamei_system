package com.dashboard.ticket.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ticket")
public class TicketEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private  String  title ;

    private String ticketType;

    private String ticketPrios;

    private String description;

    private Date startDate;

    private Date endDate;

    private boolean deadLine;

    public TicketEntity(String title, String ticketType, String ticketPrios, String description, Date startDate, Date endDate, boolean deadLine) {
        this.title = title;
        this.ticketType = ticketType;
        this.ticketPrios = ticketPrios;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.deadLine = deadLine;
    }
}
