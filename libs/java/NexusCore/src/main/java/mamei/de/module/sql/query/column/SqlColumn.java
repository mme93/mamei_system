package mamei.de.module.sql.query.column;

import mamei.de.core.exception.NexusCoreMethodNotImplementedException;

public class SqlColumn implements ISqlColumn {
    
    @Override
    public String toSql() {
        throw new NexusCoreMethodNotImplementedException("Not implemented");
    }

    @Override
    public String getAction() {
        throw new NexusCoreMethodNotImplementedException("Not implemented");
    }
}
