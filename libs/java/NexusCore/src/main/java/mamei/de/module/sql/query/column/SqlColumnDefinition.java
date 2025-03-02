package mamei.de.module.sql.query.column;

import mamei.de.core.utils.CheckValue;
import mamei.de.core.utils.CompareValue;
import mamei.de.module.sql.query.constraints.ISqlConstraint;
import mamei.de.module.sql.query.datatyp.ISqlDataTyp;

public class SqlColumnDefinition implements ISqlColumn {

    private String name;
    private ISqlDataTyp dataTyp;
    private ISqlConstraint constrain;

    public SqlColumnDefinition(String name, ISqlDataTyp dataTyp, ISqlConstraint constrain) {
        CheckValue.isNotBlank(name, "name");
        CheckValue.isNotNull(dataTyp, "dataTyp");
        this.name = name;
        this.dataTyp = dataTyp;
        this.constrain = constrain;
    }

    @Override
    public String toSql() {
        if (CompareValue.isNull(constrain)) {
            return String.format("%s %s", name, dataTyp.getDataTyp());
        }
        return String.format("%s %s%s", name, dataTyp.getDataTyp(), constrain.getConstraint());
    }

    @Override
    public String getAction() {
        return "COLUMN DEFINITION";
    }

    public static SqlColumnDefinitionBuilder builder() {
        return new SqlColumnDefinitionBuilder();
    }

    public static class SqlColumnDefinitionBuilder {

        private String name;
        private ISqlDataTyp dataTyp;
        private ISqlConstraint constrain;

        public SqlColumnDefinitionBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public SqlColumnDefinitionBuilder withDataTyp(ISqlDataTyp dataTyp) {
            this.dataTyp = dataTyp;
            return this;
        }

        public SqlColumnDefinitionBuilder withConstrain(ISqlConstraint constrain) {
            this.constrain = constrain;
            return this;
        }

        public SqlColumnDefinition build() {
            return new SqlColumnDefinition(name, dataTyp, constrain);
        }
    }


}
