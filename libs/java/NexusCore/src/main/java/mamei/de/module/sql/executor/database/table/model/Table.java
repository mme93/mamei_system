package mamei.de.module.sql.executor.database.table.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Table {

    private String tableName;
    private String databaseName;
    private List<Row>rows;

}
