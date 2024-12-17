package de.mameie.databasemanager.sql.query.condition;

public class SqlWhereCondition implements ISqlCondition {

    private String condition;

    private SqlWhereCondition(String name, String operator,String value) {
        condition= String.format("%s %s %s",name,operator,value);
    }

    public static ISqlCondition set(String name, String operator,String value){
        return new SqlWhereCondition(name,operator,value);
    }

    @Override
    public String getCondition() {
        return condition;
    }
}
