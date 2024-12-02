package mamei.de.mdv.system.data.entity;

import mamei.de.mdv.system.data.entity.attribute.Attribute;
import mamei.de.mdv.system.data.entity.primary.Primary;

import java.util.Map;

public class EntityMerger {
    public static Entity merge(Entity base, Entity other) {
        for (Map.Entry<String, Attribute> entry : other.getAttributes().entrySet()) {
            base.setAttribute(entry.getKey(), entry.getValue());
        }
        return base;
    }

    public static Entity split(Entity base, String... keys) {
        Entity result = new Primary(base.getIdentifier());
        for (String key : keys) {
            if (base.getAttributes().containsKey(key)) {
                result.setAttribute(key, base.getAttributes().get(key));
                base.setAttribute(key, null);
            }
        }
        return result;
    }
}
