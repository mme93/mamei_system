package mamei.de.module.sql.executor.database.table.column;

import mamei.de.core.utils.CheckValue;
import mamei.de.module.sql.connection.ConnectionCredentials;
import mamei.de.module.sql.executor.AbstractSqlExecutor;
import mamei.de.module.sql.executor.database.table.model.MetaData;
import mamei.de.module.sql.extractor.MetaDataSqlExtractor;
import mamei.de.module.sql.model.ESqlEnvironment;
import mamei.de.module.sql.query.SqlQueryCaster;
import mamei.de.module.sql.query.clause.alter.ISqlAlter;
import mamei.de.module.sql.query.clause.alter.SqlAlterAdd;
import mamei.de.module.sql.query.clause.alter.SqlAlterDrop;
import mamei.de.module.sql.query.clause.alter.SqlAlterModify;
import mamei.de.module.sql.query.column.SqlColumnDefinition;
import mamei.de.module.sql.query.constraints.ISqlConstraint;
import mamei.de.module.sql.query.datatyp.SqlDataTyp;

import java.sql.SQLException;
import java.util.List;

public class ColumnSqlExecutor extends AbstractSqlExecutor {

    private String tableName;
    private MetaDataSqlExtractor metaDataSqlExtractor;

    public ColumnSqlExecutor(ConnectionCredentials connectionContext, String tableName) throws SQLException {
        super(connectionContext, ESqlEnvironment.COLUMN);
        CheckValue.isNotBlank(tableName, "tableName");
        this.tableName = tableName;
        this.metaDataSqlExtractor = new MetaDataSqlExtractor(connectionContext);
    }

    public List<MetaData> loadMetaData() throws SQLException {
        return metaDataSqlExtractor.loadMetaData();
    }


    public boolean addColumn(MetaData metaData) {
        return addColumn(metaData, null);
    }

    public boolean addColumn(MetaData metaData, ISqlConstraint constraint) {
        CheckValue.isNotNull(metaData, "metaData");
        ISqlAlter sqlAlter = SqlAlterAdd
                .add()
                .withTable(tableName)
                .withColumn(
                        SqlColumnDefinition
                                .builder()
                                .withName(metaData.getField())
                                .withDataTyp(SqlDataTyp.setDatatype(metaData.getType()))
                                .withConstrain(constraint)
                                .build()
                )
                .build();
        return execute(SqlQueryCaster.cast(sqlAlter));
    }

    public boolean removeColumn(String columnName) {
        CheckValue.isNotBlank(columnName, "columnName");
        ISqlAlter sqlAlter = SqlAlterDrop
                .drop()
                .withTable(tableName)
                .withColumn(columnName)
                .build();
        return execute(SqlQueryCaster.cast(sqlAlter));
    }

    public boolean modifyColumn(MetaData metaData) {
        return modifyColumn(metaData, null);
    }

    public boolean modifyColumn(MetaData metaData, ISqlConstraint constraint) {
        CheckValue.isNotNull(metaData, "metaData");
        ISqlAlter sqlAlter = SqlAlterModify
                .modify()
                .withTable(tableName)
                .withColumn(SqlColumnDefinition
                        .builder()
                        .withName(metaData.getField())
                        .withDataTyp(SqlDataTyp.setDatatype(metaData.getType()))
                        .withConstrain(constraint)
                        .build())
                .build();
        return execute(SqlQueryCaster.cast(sqlAlter));
    }
}
