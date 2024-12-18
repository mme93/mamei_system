package de.mameie.databasemanager.sql.executor.table.metadata;

import de.mameie.databasemanager.sql.executor.table.TableSqlExecutor;
import de.mameie.databasemanager.sql.server.database.table.model.view.TableMetadata;
import de.mameie.databasemanager.util.check.CheckParam;
import org.apache.tomcat.util.buf.StringUtils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class TableMetaDataSqlExecutor extends TableSqlExecutor {

    private final String tableName;
    private Connection con;
    private DatabaseMetaData metaData;
    private List<String> primaryKeys = new ArrayList<>();
    private List<String> foreignKeys = new ArrayList<>();
    private List<String> uniqueKeys = new ArrayList<>();

    public TableMetaDataSqlExecutor(String serverName, String databaseName, String tableName) {
        super(serverName, databaseName, tableName);
        this.tableName = tableName;
    }

    public TableMetaDataSqlExecutor init() throws SQLException {
        con = super.createConnection();
        metaData = con.getMetaData();
        loadKeys();
        return this;
    }

    private List<String> getPrimaryKeys() throws SQLException {
        List<String> primaryKeys = new ArrayList<>();
        ResultSet resultSet = metaData.getPrimaryKeys(null, null, tableName);
        while (resultSet.next()) {
            primaryKeys.add(resultSet.getString("COLUMN_NAME"));
        }
        return primaryKeys;
    }

    private List<String> getForeignKeys() throws SQLException {
        List<String> foreignKeys = new ArrayList<>();
        ResultSet resultSet = metaData.getImportedKeys(null, null, tableName);
        while (resultSet.next()) {
            foreignKeys.add(resultSet.getString("FKCOLUMN_NAME"));
        }
        return foreignKeys;
    }

    private List<String> getUniqueKeys() throws SQLException {
        List<String> uniqueKeys = new ArrayList<>();
        ResultSet resultSet = metaData.getIndexInfo(null, null, tableName, true, false);
        while (resultSet.next()) {
            boolean nonUnique = resultSet.getBoolean("NON_UNIQUE");
            String columnName = resultSet.getString("COLUMN_NAME");
            if (!nonUnique && columnName != null) {
                uniqueKeys.add(columnName);
            }
        }
        return uniqueKeys;
    }

    public Optional<String> hasKey(String columnName, String isAutoIncrement) {
        if (isAutoIncrement.contains("YES")) {
            return Optional.of("PK AUTO INCREMENT");
        } else if (primaryKeys.contains(columnName)) {
            return Optional.of("PRIMARY KEY");
        } else if (foreignKeys.contains(columnName)) {
            return Optional.of("FOREIGN KEY");
        } else if (uniqueKeys.contains(columnName)) {
            return Optional.of("UNIQUE");
        }
        return Optional.empty();
    }

    public List<TableMetadata> loadMetaData() throws SQLException {
        CheckParam.isNotNull(con, "con");
        CheckParam.isNotNull(metaData, "metaData");
        List<TableMetadata> tableMetadata = new ArrayList<>();
        ResultSet columns = metaData.getColumns(null, null, tableName, null);
        String columnName;
        String autoIncrement;
        Boolean isNullable;
        while (columns.next()) {
            columnName = columns.getString("COLUMN_NAME");
            autoIncrement = columns.getString("IS_AUTOINCREMENT");
            isNullable = columns.getInt("NULLABLE") == DatabaseMetaData.columnNullable;
            tableMetadata.add(new TableMetadata(
                    columnName,
                    getColumnType(columns.getString("TYPE_NAME"), columns.getInt("COLUMN_SIZE"), columnName),
                    isNullable.toString(),
                    hasKey(columnName, autoIncrement).isPresent() ? hasKey(columnName, autoIncrement).get() : " - ",
                    columns.getString("COLUMN_DEF")
            ));
        }
        Set<String> fieldSet = new HashSet<>();
        return tableMetadata.stream()
                .filter(obj -> fieldSet.add(obj.getField()))
                .collect(Collectors.toList());
    }


    private String getColumnType(String columnType, int columnSize, String columnName) throws SQLException {
        if (columnType.equalsIgnoreCase("enum")) {
            String query = String.format("SHOW COLUMNS FROM %s LIKE '%s'", tableName, columnName);
            ResultSet enumValues = con.prepareStatement(query).executeQuery();
            if (enumValues.next()) {
                String enumValueString = enumValues.getString("Type");
                String[] enumValuesArray = enumValueString.substring(enumValueString.indexOf("(") + 1, enumValueString.indexOf(")")).split(",");
                return String.format("%s(%s)", columnType, StringUtils.join(Arrays.asList(enumValuesArray), ','));
            }

        }
        return String.format("%s(%s)", columnType, columnSize);
    }

    private void loadKeys() throws SQLException {
        primaryKeys = getPrimaryKeys();
        foreignKeys = getForeignKeys();
        uniqueKeys = getUniqueKeys();
    }
}
