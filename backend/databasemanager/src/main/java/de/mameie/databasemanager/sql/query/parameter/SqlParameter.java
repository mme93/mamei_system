package de.mameie.databasemanager.sql.query.parameter;

public class SqlParameter implements ISqlParameter {

    private String parameter;

    private SqlParameter(String name) {
        parameter = name;
    }

    public static ISqlParameter set(String name) {
        return new SqlParameter(name);
    }


    @Override
    public String getParameter() {
        return parameter;
    }
}
