package mamei.de.mdv.entity;


import mamei.de.mdv.entity.attribute.Attribute;

import java.util.ArrayList;
import java.util.List;

public abstract class Entity {

    private final String identifier;
    private final List<Attribute> attributes;

    public Entity(String identifier) {
        this.identifier = identifier;
        this.attributes = new ArrayList<>();
    }

    public String getIdentifier() {
        return identifier;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void addAttribute(Attribute attribute) {
        if (!attributes.contains(attribute)) {
            attributes.add(attribute);
        }
    }

    public void removeAttribute(Attribute attribute) {
        attributes.remove(attribute);
    }

    @Override
    public String toString() {
        return String.format("Entity{identifier='%s', attributes=%s}",
                identifier, attributes);
    }
}
