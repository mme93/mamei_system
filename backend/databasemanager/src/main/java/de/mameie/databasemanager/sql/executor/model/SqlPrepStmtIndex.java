package de.mameie.databasemanager.sql.executor.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * The {@code SqlPrepStmtIndex} class represents a model for storing an index and its associated parameter
 * in the context of SQL prepared statements. This class is useful for mapping parameters to their
 * respective indices in a prepared statement.
 */
@Getter
@Setter
@AllArgsConstructor
public class SqlPrepStmtIndex {
    /**
     * The index of the parameter in the prepared statement.
     */
    private int index;

    /**
     * The parameter value associated with the index.
     */
    private String parameter;
}
