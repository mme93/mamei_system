package mamei.de.module.sql.extractor;

import mamei.de.core.utils.CheckValue;
import mamei.de.module.sql.connection.ConnectionCredentials;
import mamei.de.module.sql.connection.ConnectionManager;
import mamei.de.module.sql.executor.database.table.model.MetaData;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class MetaDataSqlExtractor {

    private ConnectionCredentials connectionContext;
    private DatabaseMetaData metaData;
    private Connection connection;

    private List<String> primaryKeys = new ArrayList<>();
    private List<String> foreignKeys = new ArrayList<>();
    private List<String> uniqueKeys = new ArrayList<>();

    public MetaDataSqlExtractor(ConnectionCredentials connectionContext) throws SQLException {
        this.connection = ConnectionManager.getInstance(connectionContext).getConnection();
        this.metaData = connection.getMetaData();
        this.connectionContext = connectionContext;
    }

    public List<String> getPrimaryKeys() throws SQLException {
        List<String> primaryKeys = new ArrayList<>();
        ResultSet resultSet = metaData.getPrimaryKeys(null, null, connectionContext.getTableName());
        while (resultSet.next()) {
            primaryKeys.add(resultSet.getString("COLUMN_NAME"));
        }
        return primaryKeys;
    }

    public List<String> getForeignKeys() throws SQLException {
        List<String> foreignKeys = new ArrayList<>();
        ResultSet resultSet = metaData.getImportedKeys(null, null, connectionContext.getTableName());
        while (resultSet.next()) {
            foreignKeys.add(resultSet.getString("FKCOLUMN_NAME"));
        }
        return foreignKeys;
    }

    public List<String> getUniqueKeys() throws SQLException {
        List<String> uniqueKeys = new ArrayList<>();
        ResultSet resultSet = metaData.getIndexInfo(null, null, connectionContext.getTableName(), true, false);
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

    public List<MetaData> loadMetaData() throws SQLException {
        CheckValue.isNotNull(connection, "connection");
        CheckValue.isNotNull(metaData, "metaData");
        List<MetaData> tableMetadata = new ArrayList<>();
        ResultSet columns = metaData.getColumns(null, null, connectionContext.getTableName(), null);
        String columnName;
        String autoIncrement;
        Boolean isNullable;
        while (columns.next()) {
            columnName = columns.getString("COLUMN_NAME");
            autoIncrement = columns.getString("IS_AUTOINCREMENT");
            isNullable = columns.getInt("NULLABLE") == DatabaseMetaData.columnNullable;
            tableMetadata.add(new MetaData(
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
            String query = String.format("SHOW COLUMNS FROM %s LIKE '%s'", connectionContext.getTableName(), columnName);
            ResultSet enumValues = connection.prepareStatement(query).executeQuery();
            if (enumValues.next()) {
                String enumValueString = enumValues.getString("Type");
                String[] enumValuesArray = enumValueString.substring(enumValueString.indexOf("(") + 1, enumValueString.indexOf(")")).split(",");
                return String.format("%s(%s)", columnType, String.join(",",Arrays.asList(enumValuesArray)));

            }

        }
        return String.format("%s(%s)", columnType, columnSize);
    }

}
