package de.mameie.databasemanager.sql.query.field;

import de.mameie.databasemanager.sql.query.constraints.ISqlConstraint;
import de.mameie.databasemanager.sql.query.datatypes.ISqlDatatype;

public class SqlFieldDefinitionWithConstrains implements ISqlFieldDefinition {

    private String fieldDefinitionWithConstraints;

    private SqlFieldDefinitionWithConstrains(String name, String dataType) {
        fieldDefinitionWithConstraints = String.format("%s %s", name, dataType);
    }

    public static SqlFieldDefinitionWithConstrains set(String name, ISqlDatatype dataType, ISqlConstraint constraint) {
        return new SqlFieldDefinitionWithConstrains(name, String.format("%s%s", dataType.getDatatype().toUpperCase(), constraint.getConstraint()));
    }


    @Override
    public String getFieldDefinition() {
        return fieldDefinitionWithConstraints;
    }
}
