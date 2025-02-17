package mamei.de.module.sql.query.clause.show;

import mamei.de.core.utils.CheckParam;
import mamei.de.module.sql.query.ISqlQuery;

import static mamei.de.module.sql.model.DatabaseElements.*;

public class SqlShow implements ISqlQuery {

    private String content;
    private EDatabaseElements databaseElements;

    private SqlShow(String content, EDatabaseElements databaseElements) {
        if(!(EDatabaseElements.DATABASES.name()==databaseElements.name())){
            CheckParam.isNotBlank(content, "content");
        }
        CheckParam.isNotNull(databaseElements, "databaseElements");
        this.content = content;
        this.databaseElements = databaseElements;
    }

    @Override
    public String toSql() {
        if(content!=null){
            return String.format("SHOW %s %s", databaseElements.name(), content);
        }
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

        private String content;
        private EDatabaseElements databaseElements;

        public SqlShowBuilder showDatabase() {
            this.databaseElements = EDatabaseElements.DATABASES;
            return this;
        }

        public SqlShowBuilder showTables(String databaseName) {
            this.databaseElements = EDatabaseElements.DATABASE;
            this.content=databaseName;
            return this;
        }

        public SqlShow build() {
            return new SqlShow(content, databaseElements);
        }
    }
}
