package mamei.de.module.sql.query.dataset;

import mamei.de.core.utils.CheckValue;

import java.util.HashMap;
import java.util.Map;

public class SqlDataset implements ISqlDataset {

    private Map<String, Object> values;

    private SqlDataset(Map<String, Object> values) {
        CheckValue.isNotEmpty(values, "values");
        this.values = values;
    }

    @Override
    public Map<String, Object> getDataset() {
        return values;
    }

    public static ISqlDatasetBuilder builder() {
        return new ISqlDatasetBuilder();
    }

    public static class ISqlDatasetBuilder {

        private Map<String, Object> values = new HashMap<>();

        public ISqlDatasetBuilder addData(String column, Object value) {
            values.put(column, value);
            return this;
        }

        public SqlDataset build() {
            return new SqlDataset(values);
        }
    }
}
