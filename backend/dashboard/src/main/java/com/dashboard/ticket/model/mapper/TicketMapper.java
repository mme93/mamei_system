package com.dashboard.ticket.model.mapper;

import com.dashboard.ticket.model.CreateTicketDto;
import com.dashboard.ticket.model.ETicketStatus;
import com.dashboard.ticket.model.TicketEntity;

public class TicketMapper {

    public static TicketEntity createTicketMapToTicket(CreateTicketDto ticket){
        return new TicketEntity(
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
