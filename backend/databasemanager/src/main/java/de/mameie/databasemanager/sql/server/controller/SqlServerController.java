package de.mameie.databasemanager.sql.server.controller;

import de.mameie.databasemanager.sql.server.database.model.SqlDatabaseOverview;
import de.mameie.databasemanager.sql.server.service.SqlServerService;
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
@RequestMapping("/server")
public class SqlServerController {

    private final SqlServerService sqlServerService;

    @Autowired
    public SqlServerController(SqlServerService sqlServerService) {
        this.sqlServerService = sqlServerService;
    }

    @GetMapping("/{serverName}/overview")
    public ResponseEntity<List<SqlDatabaseOverview>> getServerOverview(@PathVariable String serverName){
        try {
            List<SqlDatabaseOverview>sqlDatabaseOverviews  = sqlServerService.getServerOverview(serverName);
            if(sqlDatabaseOverviews.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(sqlDatabaseOverviews,HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
