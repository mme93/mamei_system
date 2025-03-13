package mamei.de.module.sql.query.condition;

import mamei.de.core.utils.CheckValue;
import mamei.de.module.sql.query.operator.ESqlLogicOperator;

public class SqlConditionOperator implements ISqlCondition {

    private String condition;

    private SqlConditionOperator(ISqlCondition firstCondition, ISqlCondition secondCondition, ESqlLogicOperator operator) {
        CheckValue.isNotNull(firstCondition, "firstCondition");
        CheckValue.isNotNull(secondCondition, "secondCondition");
        condition = String.format("%s %s %s", firstCondition.getCondition(), operator.name(), secondCondition.getCondition());
    }

    @Override
    public String getCondition() {
        return condition;
    }

    public static SqlLogicOperatorBuilder operator() {
        return new SqlLogicOperatorBuilder();
    }


    public static class SqlLogicOperatorBuilder {
        private ISqlCondition firstCondition;
        private ISqlCondition secondCondition;

        public SqlLogicOperatorBuilder withConditions(ISqlCondition firstCondition, ISqlCondition secondCondition) {
            this.firstCondition = firstCondition;
            this.secondCondition = secondCondition;
            return this;
        }

        public SqlConditionOperator as(ESqlLogicOperator operator) {
            return new SqlConditionOperator(firstCondition, secondCondition, operator);
        }

    }

}
