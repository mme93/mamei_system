package mamei.de.mdv.datasets;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataSet {

    private final List<Map<String, Object>> data;

    public DataSet() {
        this.data = new ArrayList<>();
    }

    public void addData(Map<String, Object> entityData) {
        data.add(entityData);
    }

    public List<Map<String, Object>> getData() {
        return data;
    }
}
