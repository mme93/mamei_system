package com.apigateway.api.micro_services.dashboard.ticket.controller;

import com.apigateway.api.micro_services.dashboard.ticket.service.DashboardTicketFilterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dashboard/ticket")
public class DashboardTicketFilterController {

    private final DashboardTicketFilterService ticketFilterService;

    public DashboardTicketFilterController(DashboardTicketFilterService ticketFilterService) {
        this.ticketFilterService = ticketFilterService;
    }

    @GetMapping("/table")
    public ResponseEntity<List<Object>> getAllTicketTableFilter(){
        return new ResponseEntity<>(ticketFilterService.getAllTicketTableFilter(), HttpStatus.OK);
    }

    @GetMapping("/table/{id}")
    public ResponseEntity<Object> getTicketTableFilterById(@PathVariable Long id){
        return new ResponseEntity<>(ticketFilterService.getTicketTableFilterById(id),HttpStatus.OK);
    }

    @PostMapping("/table")
    public ResponseEntity<Object> createTicketTableFilter(@RequestBody Object filterCreateDto){
        return new ResponseEntity<>(ticketFilterService.createTicketTableFilter(filterCreateDto), HttpStatus.CREATED);
    }

    @PutMapping("/table")
    public ResponseEntity<Object> updateTicketTableFilter(@RequestBody Object tableFilterResponseDto){
        return new ResponseEntity<>(ticketFilterService.updateTicketTableFilter(tableFilterResponseDto), HttpStatus.OK);
    }

    @DeleteMapping("/table/{id}")
    public ResponseEntity deleteTicketTableFilterById(@PathVariable Long id){
        this.ticketFilterService.deleteTicketTableFilterById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
