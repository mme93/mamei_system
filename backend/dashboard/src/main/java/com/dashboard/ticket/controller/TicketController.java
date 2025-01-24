package com.dashboard.ticket.controller;

import com.dashboard.ticket.model.dto.CreateTicketDto;
import com.dashboard.ticket.model.enums.ETicketStatus;
import com.dashboard.ticket.model.entity.TicketEntity;
import com.dashboard.ticket.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/")
    public ResponseEntity<TicketEntity> createTicket(@RequestBody CreateTicketDto ticket){
        return new ResponseEntity(ticketService.createTicket(ticket),HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketEntity> getTicketById(@PathVariable Long id){
        Optional<TicketEntity>ticket=ticketService.getTicketById(id);
        if(ticket.isPresent()){
            return new ResponseEntity<>(ticket.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/")
    public ResponseEntity<List<TicketEntity>> getAllTickets(){
        return new ResponseEntity<>(ticketService.getAllTickets(),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTicketById(@PathVariable Long id){
        try{
            ticketService.deleteTicketById(id);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<TicketEntity>updateTicketStatus(@RequestBody String status,@PathVariable Long id){
        ETicketStatus parsedStatus = ETicketStatus.valueOf(status.toUpperCase());
        return new ResponseEntity<>(ticketService.updateTicketStatus(parsedStatus,id),HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<TicketEntity>updateTicket(@RequestBody TicketEntity ticket){
        return new ResponseEntity<>(ticketService.updateTicket(ticket),HttpStatus.OK);
    }

}
