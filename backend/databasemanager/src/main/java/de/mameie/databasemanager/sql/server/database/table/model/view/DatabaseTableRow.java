package de.mameie.databasemanager.sql.server.database.table.model.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DatabaseTableRow {

    private int index;

    private List<DatabaseTableCell> databaseTableCells;

    public void addCell(DatabaseTableCell cell){
        databaseTableCells.add(cell);
    }

    public void removeCell(DatabaseTableCell cell){
        databaseTableCells.remove(cell);
    }
}
