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

    @Enumerated(EnumType.STRING)
    private ETicketTyp type;

    @Enumerated(EnumType.STRING)
    private ETicketLabel label;

    @Enumerated(EnumType.STRING)
    private ETicketClassification classification;

    @Enumerated(EnumType.STRING)
    private ETicketStatus status;

    private String description;

    private Date startDate;

    private Date endDate;

    private Date createDate;

    private boolean deadLine;

    public TicketEntity(String title, ETicketTyp type, ETicketLabel label, ETicketClassification classification, ETicketStatus status, String description, Date startDate, Date endDate, Date createDate, boolean deadLine) {
        this.title = title;
        this.type = type;
        this.label = label;
        this.classification = classification;
        this.status = status;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createDate = createDate;
        this.deadLine = deadLine;
    }
}
