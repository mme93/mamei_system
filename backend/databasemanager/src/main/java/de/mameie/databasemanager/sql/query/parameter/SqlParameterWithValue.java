package de.mameie.databasemanager.sql.query.parameter;

import de.mameie.databasemanager.util.check.CheckParam;

public class SqlParameterWithValue implements ISqlParameter {

    private String parameter;

    private SqlParameterWithValue(String name, String value, String operator) {
        CheckParam.isNotNull(name,"name");
        CheckParam.isNotNull(value,"value");
        CheckParam.isNotNull(operator,"operator");
        parameter = String.format("%s %s %s", name, operator, value);
    }

    private ISqlParameter set(String name, String value, String operator) {
        return new SqlParameterWithValue(name, value, operator);
    }


    @Override
    public String getParameter() {
        return parameter;
    }
}
