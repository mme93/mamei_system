package com.dashboard.ticket.model.mapper;

import com.dashboard.ticket.model.dto.CreateTicketDto;
import com.dashboard.ticket.model.dto.TicketDto;
import com.dashboard.ticket.model.enums.ETicketStatus;
import com.dashboard.ticket.model.entity.TicketEntity;

public class TicketMapper {

    public static TicketEntity createTicketMapToTicket(CreateTicketDto ticket){
        return new TicketEntity(
                null,
                ticket.getTitle(),
                ticket.getProject(),
                ticket.getSystem(),
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

    public static TicketDto mapToDto(TicketEntity ticket) {
        return new TicketDto(
                ticket.getId(),
                ticket.getProjectId(),
                ticket.getTitle(),
                ticket.getProject(),
                ticket.getSystem(),
                ticket.getType(),
                ticket.getLabel(),
                ticket.getClassification(),
                ticket.getStatus(),
                ticket.getDescription(),
                ticket.getStartDate(),
                ticket.getEndDate(),
                ticket.getCreateDate(),
                ticket.isDeadLine()
        );
    }

    public static TicketEntity mapToEntity(TicketDto ticket) {
        return new TicketEntity(
                ticket.getId(),
                ticket.getProjectId(),
                ticket.getTitle(),
                ticket.getProject(),
                ticket.getSystem(),
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
