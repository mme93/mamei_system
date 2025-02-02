package com.apigateway.api.micro_services.dashboard.ticket.controller;

import com.apigateway.api.micro_services.dashboard.ticket.service.DashboardTicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dashboard/ticket")
public class DashboardTicketController {

    private final DashboardTicketService dashboardTicketService;

    public DashboardTicketController(DashboardTicketService dashboardTicketService) {
        this.dashboardTicketService = dashboardTicketService;
    }

    @PostMapping("/")
    public ResponseEntity<Object> createTicket(@RequestBody Object ticket){
        return new ResponseEntity(dashboardTicketService.createTicket(ticket), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTicketById(@PathVariable Long id){
        return new ResponseEntity<>(dashboardTicketService.getTicketById(id),HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Object>> getAllTickets(){
        return new ResponseEntity<>(dashboardTicketService.getAllTickets(),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTicketById(@PathVariable Long id){
        dashboardTicketService.deleteTicketById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<Object>updateTicketStatus(@RequestBody String status,@PathVariable Long id){
        return new ResponseEntity<>(dashboardTicketService.updateTicketStatus(status,id),HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<Object>updateTicket(@RequestBody Object ticket){
        return new ResponseEntity<>(dashboardTicketService.updateTicket(ticket),HttpStatus.OK);
    }

}
