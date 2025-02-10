package com.dashboard.ticket.model.entity;

import com.dashboard.ticket.model.enums.*;
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

    private String projectId;

    private  String  title ;

    @Enumerated(EnumType.STRING)
    private EProject project;

    @Enumerated(EnumType.STRING)
    private ESystemTyp system;

    @Enumerated(EnumType.STRING)
    private ETicketTyp type;

    @Enumerated(EnumType.STRING)
    private ETicketLabel label;

    @Enumerated(EnumType.STRING)
    private ETicketClassification classification;

    @Enumerated(EnumType.STRING)
    private ETicketStatus status;

    @Column(length = 2000)
    private String description;

    private Date startDate;

    private Date endDate;

    private Date createDate;

    private boolean deadLine;

    public TicketEntity(String projectId, String title, EProject project, ESystemTyp system, ETicketTyp type, ETicketLabel label, ETicketClassification classification, ETicketStatus status, String description, Date startDate, Date endDate, Date createDate, boolean deadLine) {
        this.projectId = projectId;
        this.title = title;
        this.project = project;
        this.system = system;
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
