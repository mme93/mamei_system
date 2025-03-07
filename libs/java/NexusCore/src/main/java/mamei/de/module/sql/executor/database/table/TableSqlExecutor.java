package mamei.de.module.sql.executor.database.table;

import mamei.de.core.utils.CheckValue;
import mamei.de.module.sql.connection.ConnectionCredentials;
import mamei.de.module.sql.executor.AbstractSqlExecutor;
import mamei.de.module.sql.executor.database.table.model.MetaData;
import mamei.de.module.sql.executor.database.table.row.RowSqlExecutor;
import mamei.de.module.sql.extractor.MetaDataSqlExtractor;
import mamei.de.module.sql.model.ESqlEnvironment;
import mamei.de.module.sql.executor.database.table.model.Table;
import mamei.de.module.sql.query.ISqlQuery;
import mamei.de.module.sql.query.SqlQueryCaster;
import mamei.de.module.sql.query.clause.alter.ISqlAlter;
import mamei.de.module.sql.query.clause.alter.SqlAlterPrivileges;
import mamei.de.module.sql.query.clause.create.SqlCreate;
import mamei.de.module.sql.query.clause.drop.SqlDrop;
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
    private RowSqlExecutor rowSqlExecutor;

    public TableSqlExecutor(ConnectionCredentials connectionContext) throws SQLException {
        super(connectionContext, ESqlEnvironment.TABLE);
        CheckValue.isNotBlank(connectionContext.getDatabaseName(), "database");
        this.rowSqlExecutor = new RowSqlExecutor(connectionContext);
        this.metaDataSqlExtractor = new MetaDataSqlExtractor(connectionContext);
        this.connectionContext = connectionContext;
    }

    public List<MetaData> loadMetaData() throws SQLException {
        return metaDataSqlExtractor.loadMetaData();
    }

    public boolean addDataset(ISqlDataset dataset, String tableName) {
        return rowSqlExecutor.addRow(dataset, tableName);
    }

    public boolean addDatasets(List<ISqlDataset> datasets, String tableName) {
        return rowSqlExecutor.addRows(datasets, tableName);
    }

    public Table loadData() throws SQLException {
        return new Table(
                connectionContext.getTableName(),
                connectionContext.getDatabaseName(),
                rowSqlExecutor.loadRows()
        );
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
        ISqlAlter alter = SqlAlterPrivileges
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
