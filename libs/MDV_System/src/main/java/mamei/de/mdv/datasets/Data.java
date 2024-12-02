package mamei.de.mdv.datasets;

import java.util.HashMap;
import java.util.Map;

public class Data implements IData {

    private String entityIdentifier;
    private Map<String, Object> attributes;

    public Data(String entityIdentifier) {
        this.entityIdentifier = entityIdentifier;
        this.attributes = new HashMap<>();
    }

    @Override
    public void setAttribute(String key, Object value) {
        attributes.put(key, value);
    }

    @Override
    public Object getAttribute(String key) {
        return attributes.get(key);
    }

    @Override
    public Map<String, Object> getAllAttributes() {
        return attributes;
    }

    @Override
    public String getEntityIdentifier() {
        return entityIdentifier;
    }
}
