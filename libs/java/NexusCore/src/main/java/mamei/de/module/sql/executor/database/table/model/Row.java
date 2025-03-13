package mamei.de.module.sql.executor.database.table.model;

import java.util.ArrayList;
import java.util.List;

public class Row {

    private List<String> values;

    public Row() {
        this.values = new ArrayList<>();
    }

    public Row(List<String> values) {
        this.values = new ArrayList<>();
    }

    public void addRow(String value) {
        values.add(value);
    }

    public List<String> getValues() {
        return values;
    }

    public void clear() {
        values.clear();
    }
}
