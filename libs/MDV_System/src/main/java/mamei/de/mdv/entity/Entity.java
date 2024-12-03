package mamei.de.mdv.entity;


import mamei.de.mdv.entity.attribute.Attribute;
import java.util.HashMap;
import java.util.Map;

public abstract class Entity {

    private final String identifier;
    private final Map<String, Attribute> attributes;

    public Entity(String identifier) {
        this.identifier = identifier;
        this.attributes = new HashMap<>();
    }

    public String getIdentifier() {
        return identifier;
    }

    public Map<String, Attribute> getAttributes() {
        return attributes;
    }

    public void addAttribute(String name) {
        if (!attributes.containsKey(name)) {
            attributes.put(name, new Attribute(identifier, name));
        }
    }

    public void removeAttribute(String name) {
        attributes.remove(name);
    }

    @Override
    public String toString() {
        return String.format("Entity{identifier='%s', attributes=%s}",
                identifier, attributes);
    }
}
