package mamei.de.module.sql.query.column;

import mamei.de.module.sql.query.constraints.ISqlConstraint;
import mamei.de.module.sql.query.datatyp.ISqlDataTyp;

public class SqlColumnDefinition {

    public SqlColumnDefinition() {
    }

    public static SqlColumnDefinitionBuilder builder() {
        return new SqlColumnDefinitionBuilder();
    }

    public static class SqlColumnDefinitionBuilder {

        private String name;

        public SqlColumnDefinitionBuilder withName(String name){

            return this;
        }

        public SqlColumnDefinitionBuilder withDataTyp(ISqlDataTyp dataTyp){

            return this;
        }

        public SqlColumnDefinitionBuilder withConstrain(ISqlConstraint constrain){

            return this;
        }

        public SqlColumnDefinition build() {
            return new SqlColumnDefinition();
        }
    }


}
