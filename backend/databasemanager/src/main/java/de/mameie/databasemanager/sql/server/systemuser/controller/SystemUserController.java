package de.mameie.databasemanager.sql.server.systemuser.controller;

import de.mameie.databasemanager.sql.server.systemuser.model.SystemUser;
import de.mameie.databasemanager.sql.server.systemuser.service.SystemUserService;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/systemuser")
public class SystemUserController {

    private final SystemUserService systemUserService;

    @Autowired
    public SystemUserController(SystemUserService systemUserService) {
        this.systemUserService = systemUserService;
    }

    @GetMapping("/ping")
    public ResponseEntity<String>getPing(){
        return new ResponseEntity<>("Ping",HttpStatus.OK);
    }

    @GetMapping("/{server}/all")
    public ResponseEntity<List<SystemUser>> getAllSystemUsers(@PathVariable String server) {
        try {
            List<SystemUser> systemUsers = systemUserService.getAllSystemUsers(server);
            if (systemUsers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(systemUsers, HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
