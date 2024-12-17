package de.mameie.databasemanager.sql.executor.table.metadata.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TableReferences {
    private String columnName;
    private String references;
}
