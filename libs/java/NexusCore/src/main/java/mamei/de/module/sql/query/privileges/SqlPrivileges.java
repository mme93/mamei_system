package mamei.de.module.sql.query.privileges;

import mamei.de.core.utils.CheckValue;
import mamei.de.module.sql.query.ISqlQuery;

import java.util.ArrayList;
import java.util.List;

public class SqlPrivileges implements ISqlQuery {

    private String privileges;

    private SqlPrivileges(String privileges) {
        CheckValue.isNotBlank(privileges, "privileges");
        this.privileges = privileges;
    }

    @Override
    public String toSql() {
        return privileges;
    }

    @Override
    public String getAction() {
        return "PRIVILEGES";
    }

    public static SqlPrivilegesBuilder builder() {
        return new SqlPrivilegesBuilder();
    }

    public static class SqlPrivilegesBuilder {

        private List<ESqlPrivilegesTyp> sqlPrivilegesTypList = new ArrayList<>();
        private boolean hasAllPrivileges = false;

        public SqlPrivilegesBuilder grantAll() {
            hasAllPrivileges = true;
            return this;
        }

        public SqlPrivilegesBuilder grantTyp(ESqlPrivilegesTyp sqlPrivilegesTyp) {
            CheckValue.isNotNull(sqlPrivilegesTyp, "sqlPrivilegesTyp");
            sqlPrivilegesTypList.add(sqlPrivilegesTyp);
            return this;
        }

        public SqlPrivilegesBuilder grantTyp(List<ESqlPrivilegesTyp> sqlPrivilegesTyp) {
            CheckValue.isNotEmpty(sqlPrivilegesTyp, "sqlPrivilegesTyp");
            sqlPrivilegesTypList.addAll(sqlPrivilegesTyp);
            return this;
        }

        public SqlPrivileges build() {
            if (hasAllPrivileges) {
                return new SqlPrivileges(ESqlPrivilegesTyp.ALL_PRIVILEGES.getPrivilege());
            }
            CheckValue.isNotEmpty(sqlPrivilegesTypList, "sqlPrivilegesTypList");
            return new SqlPrivileges(String.join(",", sqlPrivilegesTypList.stream().map(ESqlPrivilegesTyp::getPrivilege).toList()));
        }

    }
}
