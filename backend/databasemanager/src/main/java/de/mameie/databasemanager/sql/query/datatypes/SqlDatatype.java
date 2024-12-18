package de.mameie.databasemanager.sql.query.datatypes;

public class SqlDatatype implements ISqlDatatype {

    private static final String INT = "INT";
    private static final String CHAR = "CHAR";
    private static final String VARCHAR = "VARCHAR";
    private static final String TEXT = "TEXT";
    private static final String BINARY = "BINARY";
    private static final String DATE = "DATE";
    private static final String TIME = "TIME";
    private static final String DATETIME = "DATETIME";
    private static final String BOOLEAN = "BOOLEAN ";
    private static final String ENUM = "ENUM";
    private static final String SET = "SET";



    private String dataType;

    private SqlDatatype(String dataType) {
        this.dataType = dataType;
    }

    public static ISqlDatatype setDatatype(String datatype) {
        return new SqlDatatype(datatype);
    }

    public static ISqlDatatype INT() {
        return new SqlDatatype(INT);
    }

    public static ISqlDatatype CHAR() {
        return new SqlDatatype(CHAR);
    }

    public static ISqlDatatype VARCHAR() {
        return new SqlDatatype(VARCHAR);
    }

    public static ISqlDatatype TEXT() {
        return new SqlDatatype(TEXT);
    }

    public static ISqlDatatype BINARY() {
        return new SqlDatatype(BINARY);
    }

    public static ISqlDatatype DATE() {
        return new SqlDatatype(DATE);
    }

    public static ISqlDatatype TIME() {
        return new SqlDatatype(TIME);
    }

    public static ISqlDatatype DATETIME() {
        return new SqlDatatype(DATETIME);
    }

    public static ISqlDatatype BOOLEAN() {
        return new SqlDatatype(BOOLEAN);
    }

    public static ISqlDatatype ENUM() {
        return new SqlDatatype(ENUM);
    }

    public static ISqlDatatype SET() {
        return new SqlDatatype(SET);
    }

    @Override
    public String getDatatype() {
        return dataType;
    }
}
