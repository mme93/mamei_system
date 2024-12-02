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

    public void setAttribute(String name, Object value) {
        Attribute attribute = attributes.get(name);
        if (attribute == null) {
            attribute = new Attribute(identifier + ":" + name, name);
            attributes.put(name, attribute);
        }
        attribute.setValue(value);
    }

    public Object getAttribute(String name) {
        Attribute attribute = attributes.get(name);
        return (attribute != null) ? attribute.getValue() : null;
    }

    public void removeAttribute(String name) {
        attributes.remove(name);
    }

    @Override
    public String toString() {
        return String.format("Entity{identifier='%s', attributes=%s}", identifier, attributes);
    }
}
