package mamei.de.module.sql.data.importer;

import mamei.de.module.sql.connection.ConnectionCredentials;
import mamei.de.module.sql.executor.database.table.model.Table;
import mamei.de.module.sql.executor.database.table.row.RowSqlExecutor;
import mamei.de.module.sql.rule.MariaDBRule;
import org.junit.ClassRule;
import org.junit.Test;

import java.sql.SQLException;

import static mamei.de.core.model.EFile.CSV;
import static org.junit.Assert.assertEquals;

public class DatabaseSqlImporterTest {

    @ClassRule
    public static MariaDBRule mariaDBRule = new MariaDBRule();

    private final String tableName = "nexus_core";

    @Test
    public void shouldImportJSONFile() throws SQLException {
        ConnectionCredentials credentials = MariaDBRule.CONNECTION_CREDENTIALS;
        credentials.setTableName(tableName);
        DatabaseSqlImporter importer = new DatabaseSqlImporter(MariaDBRule.CONNECTION_CREDENTIALS);
        importer.importFromFile(CSV, "src/test/java/mamei/de/module/sql/data/importer/username.csv");
        RowSqlExecutor rowSqlExecutor = new RowSqlExecutor(credentials);
        Table table = rowSqlExecutor.loadData();
        assertEquals(tableName, table.getTableName());
    }
}
