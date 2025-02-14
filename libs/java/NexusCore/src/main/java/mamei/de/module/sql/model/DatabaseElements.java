package mamei.de.module.sql.model;

public class DatabaseElements {

    public static enum ESecurityEntities{
        USER,
        ROLE,
        PRIVILEGES,
        GRANT_OPTION
    }

    public static enum ETableElements{
        COLUMN,
        ROW
    }

    public static enum EDatabaseMetaObjects{
        SESSION,
        VARIABLES,
        STATUS
    }

    public static enum EDatabaseElements {
        TABLE,
        DATABASE,
        SCHEMA,
        INDEX,
        TRIGGER,
        PROCEDURE,
        FUNCTION
    }


}
