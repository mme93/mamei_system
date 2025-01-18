package com.dashboard.ticket.service;

import com.dashboard.ticket.model.ETicketStatus;
import com.dashboard.ticket.model.TicketEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TicketValidatorService {


    public boolean isValidStatusChange(Optional<TicketEntity> ticketOpt, ETicketStatus status){
        if(!ticketOpt.isPresent() || ticketOpt.get().getStatus() == status){
            return false;
        }
        TicketEntity  ticket =ticketOpt.get();

        if(ticket.getStatus() == ETicketStatus.CREATED){
            return isCreated(status);
        }else if(ticket.getStatus() == ETicketStatus.WAITING){
            return isWaiting(status);
        }else if(ticket.getStatus() == ETicketStatus.REFINEMENT){
            return isRefinement(status);
        }else if(ticket.getStatus() == ETicketStatus.IN_PROGRESS){
            return isInProgress(status);
        }else if(ticket.getStatus() == ETicketStatus.DONE){
            return isDone(status);
        }
        throw new IllegalArgumentException(String.format("ETicketStatus doens´t exist by status: %s",status));
    }

    public boolean isCreated(ETicketStatus status){
        return status == ETicketStatus.IN_PROGRESS;
    }
    public boolean isWaiting(ETicketStatus status){
        return status != ETicketStatus.CREATED;
    }
    public boolean isRefinement(ETicketStatus status){
        return status != ETicketStatus.CREATED;
    }
    public boolean isInProgress( ETicketStatus status){
        return status != ETicketStatus.CREATED;
    }
    public boolean isDone(ETicketStatus status){
        return status != ETicketStatus.CREATED;
    }


}
