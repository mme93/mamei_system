package de.mameie.databasemanager.sql.server.database.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SqlDatabaseOverview {

    private String databaseName;

    private List<String>tableNames;

    public void add(String tableName){
        this.tableNames.add(tableName);
    }
}
