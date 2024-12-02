package mamei.de.mdv.datasets;

import java.util.Map;

public interface IData {

    void setAttribute(String key, Object value);

    Object getAttribute(String key);

    Map<String, Object> getAllAttributes();

    String getEntityIdentifier();

}
