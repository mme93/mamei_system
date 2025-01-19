package com.dashboard.ticket.controller;

import com.dashboard.ticket.service.TicketTableSettingsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket/table/settings")
public class TicketTableSettingsController {

    private final TicketTableSettingsService settingsService;


    public TicketTableSettingsController(TicketTableSettingsService settingsService) {
        this.settingsService = settingsService;
    }

    @DeleteMapping("/{filterName}")
    public ResponseEntity deleteFilterByName(@PathVariable String filterName){
        this.settingsService.deleteFilterByName(filterName);
        return new ResponseEntity(HttpStatus.OK);
    }
}
