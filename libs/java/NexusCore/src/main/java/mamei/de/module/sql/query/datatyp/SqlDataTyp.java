package mamei.de.module.sql.query.datatyp;

public class SqlDataTyp implements ISqlDataTyp{

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

    private SqlDataTyp(String dataType) {
        this.dataType = dataType;
    }

    public static ISqlDataTyp setDatatype(String datatype) {
        return new SqlDataTyp(datatype);
    }

    public static ISqlDataTyp INT() {
        return new SqlDataTyp(INT);
    }

    public static ISqlDataTyp CHAR() {
        return new SqlDataTyp(CHAR);
    }

    public static ISqlDataTyp VARCHAR() {
        return new SqlDataTyp(VARCHAR);
    }

    public static ISqlDataTyp TEXT() {
        return new SqlDataTyp(TEXT);
    }

    public static ISqlDataTyp BINARY() {
        return new SqlDataTyp(BINARY);
    }

    public static ISqlDataTyp DATE() {
        return new SqlDataTyp(DATE);
    }

    public static ISqlDataTyp TIME() {
        return new SqlDataTyp(TIME);
    }

    public static ISqlDataTyp DATETIME() {
        return new SqlDataTyp(DATETIME);
    }

    public static ISqlDataTyp BOOLEAN() {
        return new SqlDataTyp(BOOLEAN);
    }

    public static ISqlDataTyp ENUM() {
        return new SqlDataTyp(ENUM);
    }

    public static ISqlDataTyp SET() {
        return new SqlDataTyp(SET);
    }

    @Override
    public String getDataTyp() {
        return dataType;
    }
}
