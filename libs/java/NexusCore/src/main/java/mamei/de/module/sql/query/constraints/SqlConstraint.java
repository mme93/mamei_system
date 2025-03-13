package mamei.de.module.sql.query.constraints;

public class SqlConstraint implements ISqlConstraint {

    String constraint;

    public SqlConstraint(String constraint) {
        this.constraint = constraint;
    }

    public static ISqlConstraint set(String value) {
        return new SqlConstraint(String.format("(%s)", value));
    }

    @Override
    public String getConstraint() {
        return constraint;
    }
}
