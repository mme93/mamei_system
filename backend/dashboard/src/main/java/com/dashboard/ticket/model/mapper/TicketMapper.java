package com.dashboard.ticket.model.mapper;

import com.dashboard.ticket.model.CreateTicketDto;
import com.dashboard.ticket.model.TicketEntity;

public class TicketMapper {

    public static TicketEntity createTicketMapToTicket(CreateTicketDto ticket){
        return new TicketEntity(
                ticket.getTitle(),
                ticket.getTicketType(),
                ticket.getTicketPrios(),
                ticket.getDescription(),
                ticket.getStartDate(),
                ticket.getEndDate(),
                ticket.isDeadLine()
        );
    }

}
