package com.dashboard.ticket.model.dto;

import com.dashboard.ticket.model.enums.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TicketDto {

    private Long id;

    private String projectId;

    private String title;

    private EProject project;

    private ESystemTyp system;

    private ETicketTyp type;

    private ETicketLabel label;

    private ETicketClassification classification;

    private ETicketStatus status;

    private String description;

    private Date startDate;

    private Date endDate;

    private Date createDate;

    private boolean deadLine;

}
