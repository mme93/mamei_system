package mamei.de.module.sql.query.privileges;

public enum ESqlPrivilegesTyp {
    ALL_PRIVILEGES("ALL PRIVILEGES"),
    SELECT("SELECT"),
    INSERT("INSERT"),
    UPDATE("UPDATE"),
    DELETE("DELETE"),
    CREATE("CREATE"),
    DROP("DROP"),
    REFERENCES("REFERENCES"),
    INDEX("INDEX"),
    ALTER("ALTER"),
    CREATE_TEMPORARY_TABLES("CREATE TEMPORARY TABLES"),
    LOCK_TABLES("LOCK TABLES"),
    EXECUTE("EXECUTE"),
    CREATE_VIEW("CREATE VIEW"),
    SHOW_VIEW("SHOW VIEW"),
    CREATE_ROUTINE("CREATE ROUTINE"),
    ALTER_ROUTINE("ALTER ROUTINE"),
    EVENT("EVENT"),
    TRIGGER("TRIGGER"),
    DELETE_HISTORY("DELETE HISTORY"),
    GRANT("GRANT");

    private final String privilege;

    ESqlPrivilegesTyp(String privilege) {
        this.privilege = privilege;
    }

    public String getPrivilege() {
        return privilege;
    }
}
