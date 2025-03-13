package mamei.de.module.sql.data;

import mamei.de.module.sql.data.exporter.DatabaseSqlExporterTest;
import mamei.de.module.sql.data.importer.DatabaseSqlImporterTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        DatabaseSqlExporterTest.class,
        DatabaseSqlImporterTest.class
})
public class NexusCoreDataSuite {
}
