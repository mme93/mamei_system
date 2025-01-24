package com.dashboard.ticket.model.dto;

import com.dashboard.ticket.model.enums.ETicketClassification;
import com.dashboard.ticket.model.enums.ETicketLabel;
import com.dashboard.ticket.model.enums.ETicketTyp;
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
    private ETicketTyp type;
    private ETicketLabel label;
    private ETicketClassification classification;
    private String projectLabel;

}
