package de.mameie.databasemanager.sql.server.database.table.model.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TableFieldDefinition {

    private String name;
    private String dataType;
    private String constraint;

}
