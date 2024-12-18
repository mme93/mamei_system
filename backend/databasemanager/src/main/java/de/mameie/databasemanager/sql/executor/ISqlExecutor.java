package de.mameie.databasemanager.sql.executor;

import de.mameie.databasemanager.sql.executor.model.SqlPrepStmtIndex;
import de.mameie.databasemanager.sql.executor.model.SqlPrepStmtParamName;
import de.mameie.databasemanager.sql.query.ISqlQuery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface ISqlExecutor {

    ResultSet executeQuery(ISqlQuery query);

    boolean execute(ISqlQuery query) throws SQLException;

    boolean hasResult(ISqlQuery query) throws SQLException;

    int executeUpdate(ISqlQuery query);

    int[] executeBatch(List<ISqlQuery> query);

    ResultSet executeQuery(ISqlQuery query, SqlPrepStmtIndex index);

    boolean execute(ISqlQuery query, SqlPrepStmtIndex index);

    boolean hasResult(ISqlQuery query, SqlPrepStmtIndex index);

    int executeUpdate(ISqlQuery query, SqlPrepStmtIndex index);

    int[] executeBatch(List<ISqlQuery> query, SqlPrepStmtIndex index);

    ResultSet executeQuery(ISqlQuery query, SqlPrepStmtParamName paramName);

    boolean execute(ISqlQuery query, SqlPrepStmtParamName paramName);

    boolean hasResult(ISqlQuery query, SqlPrepStmtParamName paramName);

    int executeUpdate(ISqlQuery query, SqlPrepStmtParamName paramName);

    int[] executeBatch(List<ISqlQuery> query, SqlPrepStmtParamName paramName);


}
