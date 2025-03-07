package mamei.de.module.sql.executor.database.table.row;

import mamei.de.core.utils.CheckValue;
import mamei.de.module.sql.connection.ConnectionCredentials;
import mamei.de.module.sql.executor.AbstractSqlExecutor;
import mamei.de.module.sql.executor.database.table.model.Column;
import mamei.de.module.sql.executor.database.table.model.MetaData;
import mamei.de.module.sql.executor.database.table.model.Row;
import mamei.de.module.sql.extractor.MetaDataSqlExtractor;
import mamei.de.module.sql.model.ESqlEnvironment;
import mamei.de.module.sql.query.ISqlQuery;
import mamei.de.module.sql.query.clause.insert.SqlInsert;
import mamei.de.module.sql.query.clause.select.SqlSelect;
import mamei.de.module.sql.query.dataset.ISqlDataset;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RowSqlExecutor extends AbstractSqlExecutor {

    private ConnectionCredentials connectionContext;
    private MetaDataSqlExtractor metaDataSqlExtractor;

    public RowSqlExecutor(ConnectionCredentials connectionContext) throws SQLException {
        super(connectionContext, ESqlEnvironment.ROW);
        this.connectionContext = connectionContext;
        this.metaDataSqlExtractor = new MetaDataSqlExtractor(connectionContext);
    }

    public List<Row> loadRows() throws SQLException {
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
        return rows;
    }

    public boolean addRow(ISqlDataset dataset, String tableName) {
        ISqlQuery query = SqlInsert
                .insert()
                .into(tableName)
                .addRow(dataset)
                .build();
        return execute(query);
    }

    public boolean addRows(List<ISqlDataset> datasets, String tableName) {
        ISqlQuery query = SqlInsert
                .insert()
                .into(tableName)
                .addRows(datasets)
                .build();
        return execute(query);
    }

}
