package mamei.de.module.sql.data.exporter;

import mamei.de.module.sql.connection.ConnectionCredentials;
import mamei.de.module.sql.executor.database.table.TableSqlExecutor;
import mamei.de.module.sql.executor.database.table.row.RowSqlExecutor;
import mamei.de.module.sql.query.column.ISqlColumn;
import mamei.de.module.sql.query.column.SqlColumnDefinition;
import mamei.de.module.sql.query.constraints.SqlConstraint;
import mamei.de.module.sql.query.dataset.ISqlDataset;
import mamei.de.module.sql.query.dataset.SqlDataset;
import mamei.de.module.sql.query.datatyp.SqlDataTyp;
import mamei.de.module.sql.rule.MariaDBRule;
import org.junit.ClassRule;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertTrue;

public class DatabaseSqlExporterTest {

    @ClassRule
    public static MariaDBRule mariaDBRule = new MariaDBRule();

    private final String tableName = "nexus_core";

    private TableSqlExecutor tableSqlExecutor = new TableSqlExecutor(mariaDBRule.CONNECTION_DB_CREDENTIALS);
    private RowSqlExecutor rowSqlExecutor = new RowSqlExecutor(mariaDBRule.CONNECTION_DB_CREDENTIALS);

    private final String ID = "ID";
    private final String NAME = "NAME";
    private final String AGE = "AGE";

    public DatabaseSqlExporterTest() throws SQLException {
    }

    @Test
    public void shouldExportJSONFile() throws SQLException {
        createTable(tableSqlExecutor);
        DatabaseSqlExporter exporter = new DatabaseSqlExporter(MariaDBRule.CONNECTION_CREDENTIALS);
        tableSqlExecutor.drop(tableName);
    }

    @Test
    public void shouldExportCSV() throws SQLException {
        createTable(tableSqlExecutor);
        ConnectionCredentials credentials=MariaDBRule.CONNECTION_CREDENTIALS;
        credentials.setTableName(tableName);
        DatabaseSqlExporter exporter = new DatabaseSqlExporter(credentials);
        exporter.exportCSV("src/test/java/mamei/de/module/sql/data/exporter/data.csv");
        tableSqlExecutor.drop(tableName);
    }

    private void createTable(TableSqlExecutor executor) throws SQLException {
        List<ISqlColumn> columns = new ArrayList<>();
        columns.add(SqlColumnDefinition
                .builder()
                .withName(ID)
                .withDataTyp(SqlDataTyp.INT())
                .build()
        );
        columns.add(SqlColumnDefinition
                .builder()
                .withName(NAME)
                .withDataTyp(SqlDataTyp.VARCHAR())
                .withConstrain(SqlConstraint.set("510"))
                .build()
        );
        columns.add(SqlColumnDefinition
                .builder()
                .withName(AGE)
                .withDataTyp(SqlDataTyp.INT())
                .build()
        );
        executor.create(tableName, columns);
        assertTrue(executor.exist(tableName));
        List<ISqlDataset> datasets = asList(
                SqlDataset.builder().addData(ID, 1L).addData(NAME, "Peter").addData(AGE, 18).build(),
                SqlDataset.builder().addData(ID, 1L).addData(NAME, "Frank").addData(AGE, 56).build(),
                SqlDataset.builder().addData(ID, 1L).addData(NAME, "Susi").addData(AGE, 12).build(),
                SqlDataset.builder().addData(ID, 1L).addData(NAME, "Julian").addData(AGE, 3).build()
        );
        rowSqlExecutor.addRows(datasets, tableName);
    }
}
