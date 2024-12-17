package de.mameie.databasemanager.sql.server.database.table.util;

import de.mameie.databasemanager.sql.query.constraints.SqlConstraint;
import de.mameie.databasemanager.sql.query.datatypes.SqlDatatype;
import de.mameie.databasemanager.sql.query.field.ISqlFieldDefinition;
import de.mameie.databasemanager.sql.query.field.SqlFieldDefinition;
import de.mameie.databasemanager.sql.query.field.SqlFieldDefinitionWithConstrains;
import de.mameie.databasemanager.sql.server.database.table.model.create.TableFieldDefinition;

import java.util.ArrayList;
import java.util.List;

public class FieldDefinitionConverter {

    public static List<ISqlFieldDefinition> convertToISqlFieldDefinition(List<TableFieldDefinition> fieldDefinitionList) {
        List<ISqlFieldDefinition> iSqlFieldDefinitions = new ArrayList<>();
        for (TableFieldDefinition tableFieldDefinition : fieldDefinitionList) {
            if (tableFieldDefinition.getConstraint() != null) {
                iSqlFieldDefinitions.add(SqlFieldDefinitionWithConstrains.set(
                        tableFieldDefinition.getName(),
                        SqlDatatype.setDatatype(tableFieldDefinition.getDataType()),
                        SqlConstraint.set(tableFieldDefinition.getConstraint())
                ));
            } else {
                iSqlFieldDefinitions.add(SqlFieldDefinition.set(
                        tableFieldDefinition.getName(),
                        SqlDatatype.setDatatype(tableFieldDefinition.getDataType())
                ));
            }

        }
        return iSqlFieldDefinitions;
    }

}
