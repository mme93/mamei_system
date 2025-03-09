package mamei.de.module.sql.executor.database.table;

import mamei.de.module.sql.executor.database.table.model.Column;
import mamei.de.module.sql.executor.database.table.model.Table;
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
import static org.junit.Assert.*;

public class TableSqlExecutorTest {

    @ClassRule
    public static MariaDBRule mariaDBRule = new MariaDBRule();

    private final String tableName = "nexus_core";

    private TableSqlExecutor tableSqlExecutor = new TableSqlExecutor(mariaDBRule.CONNECTION_DB_CREDENTIALS);
    private RowSqlExecutor rowSqlExecutor = new RowSqlExecutor(mariaDBRule.CONNECTION_DB_CREDENTIALS);

    private final String ID = "ID";
    private final String NAME = "NAME";
    private final String AGE = "AGE";

    public TableSqlExecutorTest() throws SQLException {
    }

    @Test
    public void shouldCreateTable() throws SQLException {
        assertFalse(tableSqlExecutor.exist(tableName));
        createTable(tableSqlExecutor);
        assertTrue(tableSqlExecutor.exist(tableName));
        tableSqlExecutor.drop(tableName);
    }

    @Test
    public void shouldDeleteTable() throws SQLException {
        createTable(tableSqlExecutor);
        tableSqlExecutor.drop(tableName);
        assertFalse(tableSqlExecutor.exist(tableName));
    }

    @Test
    public void shouldLoadData() throws SQLException {
        tableSqlExecutor.setTableName(tableName);
        createTable(tableSqlExecutor);
        Table table = rowSqlExecutor.loadData();
        assertEquals(table.getTableName(), tableName);
        assertEquals(table.getRows().size(), 4);
        assertEquals(table.getDatabaseName(), mariaDBRule.CONNECTION_DB_CREDENTIALS.getDatabaseName());
        assertEquals(table.getRows().get(0).getColumns().stream().map(Column::getName).toList(),asList(ID,NAME,AGE));
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
