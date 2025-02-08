package com.dashboard.ticket.controller;

import com.dashboard.ticket.model.dto.TicketTableFilterCreateDto;
import com.dashboard.ticket.model.dto.TicketTableFilterResponseDto;
import com.dashboard.ticket.model.entity.TicketTableFilterEntity;
import com.dashboard.ticket.model.mapper.TicketFilterMapper;
import com.dashboard.ticket.service.TicketFilterService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/ticket/filter")
@RestController
public class TicketFilterController {

    private final TicketFilterService ticketFilterService;

    public TicketFilterController(TicketFilterService ticketFilterService) {
        this.ticketFilterService = ticketFilterService;
    }

    @GetMapping("/tables")
    public ResponseEntity<List<TicketTableFilterResponseDto>> getAllTicketTableFilter(@RequestHeader(value = "X-Username", required = false) String username){
        try {
            return new ResponseEntity<>(this.ticketFilterService.getAllTicketTableFilter(username), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/table")
    public ResponseEntity<TicketTableFilterResponseDto> getCurrentTicketTableFilter(@RequestHeader(value = "X-Username", required = false) String username){
        try {
            return new ResponseEntity<>(this.ticketFilterService.getCurrentTicketTableFilter(username), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/table/{id}")
    public ResponseEntity<TicketTableFilterResponseDto> getTicketTableFilterById(@PathVariable Long id){
        Optional<TicketTableFilterEntity>tableFilterEntityOpt=this.ticketFilterService.getTicketTableFilterById(id);
        if(tableFilterEntityOpt.isPresent()){
            try {
                return new ResponseEntity<>(TicketFilterMapper.entityMapToDto(tableFilterEntityOpt.get()),HttpStatus.OK);
            } catch (JsonProcessingException e) {
               return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }

    @PostMapping("/table")
    public ResponseEntity<TicketTableFilterEntity> createTicketTableFilter(@RequestBody TicketTableFilterCreateDto filterCreateDto){
        return new ResponseEntity<>(this.ticketFilterService.createTicketTableFilter(filterCreateDto), HttpStatus.CREATED);
    }

    @PutMapping("/table")
    public ResponseEntity<TicketTableFilterEntity> updateTicketTableFilter(@RequestBody TicketTableFilterResponseDto tableFilterResponseDto){
        return new ResponseEntity<>(this.ticketFilterService.updateTicketTableFilter(tableFilterResponseDto), HttpStatus.OK);
    }

    @DeleteMapping("/table/{id}")
    public ResponseEntity deleteTicketTableFilterById(@PathVariable Long id){
       this.ticketFilterService.deleteTicketTableFilterById(id);
       return new ResponseEntity<>(HttpStatus.OK);
    }


}
