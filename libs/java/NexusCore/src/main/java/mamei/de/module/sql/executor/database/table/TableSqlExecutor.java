package mamei.de.module.sql.executor.database.table;

import mamei.de.core.utils.CheckValue;
import mamei.de.module.sql.connection.ConnectionCredentials;
import mamei.de.module.sql.executor.AbstractSqlExecutor;
import mamei.de.module.sql.executor.database.table.model.Column;
import mamei.de.module.sql.executor.database.table.model.MetaData;
import mamei.de.module.sql.executor.database.table.model.Row;
import mamei.de.module.sql.extractor.MetaDataSqlExtractor;
import mamei.de.module.sql.model.ESqlEnvironment;
import mamei.de.module.sql.executor.database.table.model.Table;
import mamei.de.module.sql.query.ISqlQuery;
import mamei.de.module.sql.query.SqlQueryCaster;
import mamei.de.module.sql.query.clause.alter.ISqlAlter;
import mamei.de.module.sql.query.clause.alter.SqlAlter;
import mamei.de.module.sql.query.clause.create.SqlCreate;
import mamei.de.module.sql.query.clause.drop.SqlDrop;
import mamei.de.module.sql.query.clause.insert.SqlInsert;
import mamei.de.module.sql.query.clause.select.SqlSelect;
import mamei.de.module.sql.query.clause.show.SqlShow;
import mamei.de.module.sql.query.column.ISqlColumn;
import mamei.de.module.sql.query.dataset.ISqlDataset;
import mamei.de.module.sql.query.privileges.ESqlPrivilegesTyp;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TableSqlExecutor extends AbstractSqlExecutor {

    private MetaDataSqlExtractor metaDataSqlExtractor;
    private ConnectionCredentials connectionContext;

    public TableSqlExecutor(ConnectionCredentials connectionContext) throws SQLException {
        super(connectionContext, ESqlEnvironment.TABLE);
        CheckValue.isNotBlank(connectionContext.getDatabaseName(), "database");
        this.metaDataSqlExtractor = new MetaDataSqlExtractor(connectionContext);
        this.connectionContext = connectionContext;
    }

    public List<MetaData> loadMetaData() throws SQLException {
        return metaDataSqlExtractor.loadMetaData();
    }

    public boolean addRow(ISqlDataset dataset,String tableName) {
        ISqlQuery query=SqlInsert
                .insert()
                .into(tableName)
                .addRow(dataset)
                .build();
        return execute(query);
    }

    public boolean addRows(List<ISqlDataset> datasets,String tableName) {
        ISqlQuery query=SqlInsert
                .insert()
                .into(tableName)
                .addRows(datasets)
                .build();
        return execute(query);
    }

    public Table loadData() throws SQLException {
        CheckValue.isNotBlank(connectionContext.getTableName(), "tableName");
        List<MetaData> headers = metaDataSqlExtractor.loadMetaData();
        ResultSet resultSet = executeQuery(SqlSelect.builder().selectAll().from(connectionContext.getTableName()).build());
        List<Row> rows = new ArrayList<>();
        while (resultSet.next()) {
            List<Column> columns = new ArrayList<>();
            for (MetaData metaData : headers) {
                String name = metaData.getField();
                columns.add(new Column(resultSet.getString(name), name, metaData));
            }
            rows.add(new Row(columns));
        }
        return new Table(connectionContext.getTableName(), connectionContext.getDatabaseName(), rows);
    }

    public List<String> show() throws SQLException {
        List<String> results = new ArrayList<>();
        ISqlQuery sqlQuery = SqlShow.show().showTables().build();
        ResultSet resultSet = executeQuery(sqlQuery);
        while (resultSet.next()) {
            String result = resultSet.getString(1);
            results.add(result);
        }
        return results;
    }

    public boolean drop(String tableName) {
        ISqlQuery sqlQuery = SqlDrop.drop().table(tableName).build();
        return execute(sqlQuery);
    }


    public boolean create(String tableName, List<ISqlColumn> columns) {
        ISqlQuery sqlQuery = SqlCreate.create().table(tableName, columns).build();
        return execute(sqlQuery);
    }

    public boolean alter(String tableName, ESqlPrivilegesTyp sqlPrivilegesTyp, ISqlColumn column) {
        ISqlAlter alter = SqlAlter
                .alter()
                .withName(tableName)
                .withPrivileges(sqlPrivilegesTyp)
                .withColumn(column)
                .build();
        return execute(SqlQueryCaster.cast(alter));
    }

    public boolean exist(String tableName) throws SQLException {
        Connection con = getConnection();
        DatabaseMetaData metaData = con.getMetaData();
        try (ResultSet resultSet = metaData.getTables(null, null, tableName.toUpperCase(), new String[]{"TABLE"})) {
            return resultSet.next();
        }
    }

    public void setTableName(String tableName) {
        CheckValue.isNotBlank(tableName, "tableName");
        connectionContext.setTableName(tableName);
    }
}
