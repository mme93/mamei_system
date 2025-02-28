package mamei.de.module.sql.executor.database.table.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Column {

    private String value;
    private String name;
    private MetaData metaData;
}
