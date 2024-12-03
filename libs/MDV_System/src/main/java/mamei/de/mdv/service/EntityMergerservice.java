package mamei.de.mdv.service;

import mamei.de.mdv.entity.Entity;
import mamei.de.mdv.entity.attribute.Attribute;

import java.util.Map;

public class EntityMergerservice {

    public static Entity merge(Entity base, Entity other) {
        for (Map.Entry<String, Attribute> entry : other.getAttributes().entrySet()) {
            base.addAttribute(entry.getKey());
        }
        return base;
    }

    public static Entity split(Entity base, String... keys) {
        //Entity result = new Primary(base.getIdentifier());
        Entity result = null;
        for (String key : keys) {
            if (base.getAttributes().containsKey(key)) {
                result.addAttribute(key);
                base.removeAttribute(key);
            }
        }
        return result;
    }
}
