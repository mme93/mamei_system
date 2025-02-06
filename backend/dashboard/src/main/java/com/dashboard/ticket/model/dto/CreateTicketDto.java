package com.dashboard.ticket.model.dto;

import com.dashboard.ticket.model.enums.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class CreateTicketDto {

    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private Date createDate;
    private boolean deadLine;
    private EProject project;
    private ESystemTyp system;
    private ETicketTyp type;
    private ETicketLabel label;
    private ETicketClassification classification;

}