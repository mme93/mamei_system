package mamei.de.module.sql.query.clause.alter;

import org.junit.Test;

public class SqlAlterTest {

    private final String TABLE_NAME = "NEXUS_CORE";
    private final String COLUMN_NAME = "name";

    @Test
    public void shouldAlterSqlDrop() {
        ISqlAlter sqlAlter = SqlAlterDrop
                .drop()
                .withTable(TABLE_NAME)
                .withColumn(COLUMN_NAME)
                .build();
        String sql = sqlAlter.toSql();
    }
}
