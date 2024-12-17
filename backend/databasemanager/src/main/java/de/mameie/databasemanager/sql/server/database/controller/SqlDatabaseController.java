package de.mameie.databasemanager.sql.server.database.controller;

import de.mameie.databasemanager.sql.server.database.service.SqlDatabaseService;
import de.mameie.databasemanager.util.check.CheckParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/database/{serverName}")
public class SqlDatabaseController {

    private final SqlDatabaseService sqlDatabaseService;

    @Autowired
    public SqlDatabaseController(SqlDatabaseService sqlDatabaseService) {
        this.sqlDatabaseService = sqlDatabaseService;
    }

    @DeleteMapping("/delete/{databaseName}")
    public ResponseEntity deleteDatabase(@PathVariable String databaseName, @PathVariable String serverName) throws SQLException {
        CheckParam.isNotBlank(databaseName, "databaseName");
        CheckParam.isNotBlank(serverName, "serverName");
        sqlDatabaseService.deleteDatabase(databaseName, serverName);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/create/{databaseName}")
    public ResponseEntity createDatabase(@PathVariable String databaseName, @PathVariable String serverName) throws SQLException {
        CheckParam.isNotBlank(databaseName, "databaseName");
        CheckParam.isNotBlank(serverName, "serverName");
        sqlDatabaseService.createDatabase(databaseName, serverName);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<String>> getDatabaseNames(@PathVariable String serverName) {
        CheckParam.isNotBlank(serverName, "serverName");
        return new ResponseEntity(sqlDatabaseService.getDatabaseNames(serverName), HttpStatus.OK);
    }
}
