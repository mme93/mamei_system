package mamei.de.module.sql.executor.database.table.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MetaData {

    private String field;
    private String type;
    private String nullable;
    private String key;
    private String defaultValue;

}
