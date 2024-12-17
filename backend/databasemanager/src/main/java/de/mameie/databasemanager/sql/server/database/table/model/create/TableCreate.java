package de.mameie.databasemanager.sql.server.database.table.model.create;

import de.mameie.databasemanager.sql.server.database.model.SqlLoginTable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TableCreate {

    private SqlLoginTable sqlLoginTable;
    List<TableFieldDefinition> fieldDefinitionList;

}
