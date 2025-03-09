package mamei.de.module.sql.query.condition;

import mamei.de.core.utils.CheckValue;
import mamei.de.module.sql.query.clause.select.SqlSelect;

import java.util.List;

public class SqlCondition implements ISqlCondition{

    private String condition;

    private SqlCondition(String condition) {
        CheckValue.isNotBlank(condition, "condition");
        this.condition = condition;
    }

    public static SqlConditionBuilder condition() {
        return new SqlConditionBuilder();
    }

    public String getCondition() {
        return condition;
    }

    public static class SqlConditionBuilder {

        public SqlCondition comparison(String columnName, int value, String comparisonOperator) {
            return new SqlCondition(String.format("%s %s %s", columnName, comparisonOperator, value));
        }

        public SqlCondition like(String columnName, String like) {
            return new SqlCondition(String.format("%s LIKE '%s'", columnName, like));
        }

        public SqlCondition between(String columnName, int firstValue, int secondValue) {
            return new SqlCondition(String.format("%s BETWEEN %s AND %s", columnName, firstValue, secondValue));
        }

        public SqlCondition in(String columnName, List<String> values) {
            values = values.stream().map(value -> String.format("'%s'", value)).toList();
            return new SqlCondition(String.format("%s IN (%s)", columnName, String.join(",", values)));
        }

        public SqlCondition in(String columnName, SqlSelect sqlSelect) {
            return new SqlCondition(String.format("%s IN (%s)", columnName, sqlSelect.toSql()));
        }

        public SqlCondition isNull(String columnName) {
            return new SqlCondition(String.format("%s IS NULL",columnName));
        }

        public SqlCondition isNotNull(String columnName) {
            return new SqlCondition(String.format("%s IS NOT NULL",columnName));
        }

        public SqlCondition regEx(String columnName, String regEx) {
            return new SqlCondition(String.format("%s REGEXP '%s'", columnName, regEx));
        }

    }
}
