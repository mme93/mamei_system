package mamei.de.module.sql.executor.database.table;

import mamei.de.module.sql.executor.database.table.model.Table;
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

    private TableSqlExecutor executor = new TableSqlExecutor(mariaDBRule.CONNECTION_DB_CREDENTIALS);

    public TableSqlExecutorTest() throws SQLException {
    }

    @Test
    public void shouldCreateTable() throws SQLException {
        assertFalse(executor.exist(tableName));
        createTable(executor);
        assertTrue(executor.exist(tableName));
    }

    @Test
    public void shouldDeleteTable() throws SQLException {
        createTable(executor);
        executor.drop(tableName);
        assertFalse(executor.exist(tableName));
    }

    @Test
    public void shouldLoadData() throws SQLException {
        executor.setTableName(tableName);
        createTable(executor);
        Table table=executor.loadData();
        assertEquals(table.getTableName(),tableName);
        assertEquals(table.getRows().size(),4);
        assertEquals(table.getDatabaseName(),mariaDBRule.CONNECTION_DB_CREDENTIALS.getDatabaseName());
    }

    private void createTable(TableSqlExecutor executor) throws SQLException {
        final String ID = "ID";
        final String NAME = "NAME";
        final String AGE = "AGE";
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
        executor.addRows(datasets, tableName);
    }

}
