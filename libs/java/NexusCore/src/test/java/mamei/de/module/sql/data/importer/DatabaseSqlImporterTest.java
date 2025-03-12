package mamei.de.module.sql.data.importer;

import mamei.de.module.sql.rule.MariaDBRule;
import org.junit.ClassRule;
import org.junit.Test;

public class DatabaseSqlImporterTest {

    @ClassRule
    public static MariaDBRule mariaDBRule = new MariaDBRule();

    @Test
    public void shouldImportJSONFile(){
        DatabaseSqlImporter importer = new DatabaseSqlImporter(MariaDBRule.CONNECTION_CREDENTIALS);
    }
}
