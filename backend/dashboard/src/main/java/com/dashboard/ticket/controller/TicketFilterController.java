package com.dashboard.ticket.controller;

import com.dashboard.ticket.model.entity.TicketTableFilterEntity;
import com.dashboard.ticket.service.TicketFilterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/ticket/filter")
@RestController
public class TicketFilterController {

    private final TicketFilterService ticketFilterService;

    public TicketFilterController(TicketFilterService ticketFilterService) {
        this.ticketFilterService = ticketFilterService;
    }

    @GetMapping("/table")
    public ResponseEntity<List<TicketTableFilterEntity>> getAllTicketTableFilter(){
        return new ResponseEntity<>(this.ticketFilterService.getAllTicketTableFilter(), HttpStatus.OK);
    }

    @GetMapping("/table")
    public ResponseEntity<TicketTableFilterEntity> createTicketTableFilter(){
        return new ResponseEntity<>(this.ticketFilterService.createTicketTableFilter(), HttpStatus.OK);
    }


}
