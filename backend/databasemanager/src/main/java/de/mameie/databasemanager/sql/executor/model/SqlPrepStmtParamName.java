package de.mameie.databasemanager.sql.executor.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * The {@code SqlPrepStmtParamName} class represents a model for storing a parameter value and its
 * associated parameter name in the context of SQL prepared statements. This class is useful for mapping
 * parameter names to their respective values in a prepared statement.
 */
@Getter
@Setter
@AllArgsConstructor
public class SqlPrepStmtParamName {
    /**
     * The parameter value associated with the parameter name.
     */
    private String parameter;

    /**
     * The name of the parameter in the prepared statement.
     */
    private String parameterName;
}
