package mamei.de.module.sql.query.clause.show;

import mamei.de.core.utils.CheckValue;
import mamei.de.module.sql.query.ISqlQuery;

import static mamei.de.module.sql.model.DatabaseElements.*;

public class SqlShow implements ISqlQuery {

    private EDatabaseElements databaseElements;

    private SqlShow(EDatabaseElements databaseElements) {
        CheckValue.isNotNull(databaseElements, "databaseElements");
        this.databaseElements = databaseElements;
    }

    @Override
    public String toSql() {
        return String.format("SHOW %s", databaseElements.name());
    }

    @Override
    public String getAction() {
        return "SHOW";
    }

    public static SqlShowBuilder show() {
        return new SqlShowBuilder();
    }

    public static class SqlShowBuilder {

        private EDatabaseElements databaseElements;

        public SqlShowBuilder showDatabase() {
            this.databaseElements = EDatabaseElements.DATABASES;
            return this;
        }

        public SqlShowBuilder showTables() {
            this.databaseElements = EDatabaseElements.DATABASE;
            return this;
        }

        public SqlShow build() {
            return new SqlShow(databaseElements);
        }
    }
}
