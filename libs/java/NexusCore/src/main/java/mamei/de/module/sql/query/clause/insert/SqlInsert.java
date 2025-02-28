package mamei.de.module.sql.query.clause.insert;

import mamei.de.core.exception.NexusCoreInvalidDataException;
import mamei.de.core.utils.CheckValue;
import mamei.de.module.sql.query.ISqlQuery;
import mamei.de.module.sql.query.dataset.ISqlDataset;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class SqlInsert implements ISqlQuery {

    private String tableName;
    private List<List<String>> dataSets;
    private Set<String> columnNames;

    public SqlInsert(String tableName, List<List<String>> dataSets, Set<String> columnNames) {
        CheckValue.isNotBlank(tableName, "tableName");
        CheckValue.isNotEmpty(dataSets, "dataSets");
        CheckValue.isNotEmpty(columnNames, "columnNames");
        this.tableName = tableName;
        this.dataSets = dataSets;
        this.columnNames = columnNames;
    }

    @Override
    public String toSql() {
        return String.format("INSERT INTO %s (%s) VALUES %s",
                tableName,
                String.join(", ", columnNames),
                dataSets.stream()
                        .map(list -> "(" + String.join(",", list) + ")")
                        .collect(Collectors.joining(",")));
    }

    @Override
    public String getAction() {
        return "INSERT";
    }

    public static SqlInsertBuilder insert() {
        return new SqlInsertBuilder();
    }

    public static class SqlInsertBuilder {

        private String tableName;
        private List<List<String>> dataSets = new ArrayList<>();
        private Set<String> columnNames;

        public SqlInsertBuilder into(String tableName) {
            this.tableName = tableName;
            return this;
        }

        public SqlInsertBuilder addRow(ISqlDataset dataset) {
            addRow(dataset.getDataset());
            return this;
        }

        public SqlInsertBuilder addRows(List<ISqlDataset> datasets) {
            CheckValue.isNotEmpty(datasets, "datasets");
            datasets.stream().map(ISqlDataset::getDataset).forEach(dataset -> addRow(dataset));
            return this;
        }

        public SqlInsert build() {
            return new SqlInsert(tableName, dataSets, columnNames);
        }

        private void addRow(Map<String, Object> row) {
            CheckValue.isNotEmpty(row, "row");
            if (columnNames == null) {
                columnNames = row.keySet();
            } else if (!columnNames.equals(row.keySet())) {
                throw new NexusCoreInvalidDataException(String.format("Current set (%s) is not equal to new set (%s",
                        columnNames,
                        row.keySet()
                ));
            }
            dataSets.add(row.values().stream().map(Object::toString).map(value -> String.format("'%s'", value)).toList());
        }
    }
}
