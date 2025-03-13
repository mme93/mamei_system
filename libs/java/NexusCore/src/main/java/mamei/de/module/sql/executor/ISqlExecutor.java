package mamei.de.module.sql.executor;

import mamei.de.module.sql.query.ISqlQuery;

import java.sql.ResultSet;

public interface ISqlExecutor {

    ResultSet executeQuery(ISqlQuery query);

}
