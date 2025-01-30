package com.dashboard.ticket.model.mapper;

import com.dashboard.ticket.model.dto.CreateTicketDto;
import com.dashboard.ticket.model.enums.ETicketStatus;
import com.dashboard.ticket.model.entity.TicketEntity;

public class TicketMapper {

    public static TicketEntity createTicketMapToTicket(CreateTicketDto ticket){
        return new TicketEntity(
                null,
                ticket.getProjectLabel(),
                ticket.getTitle(),
                ticket.getType(),
                ticket.getLabel(),
                ticket.getClassification(),
                ETicketStatus.CREATED,
                ticket.getDescription(),
                ticket.getStartDate(),
                ticket.getEndDate(),
                ticket.getCreateDate(),
                ticket.isDeadLine()
        );
    }

}
