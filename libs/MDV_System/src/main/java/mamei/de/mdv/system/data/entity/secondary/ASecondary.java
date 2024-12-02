package mamei.de.mdv.system.data.entity.secondary;

import mamei.de.mdv.system.data.entity.Entities;
import mamei.de.mdv.system.data.entity.attribute.Attribute;

import java.util.ArrayList;
import java.util.List;

public abstract class ASecondary implements ISecondary {

    private String identifier;
    private List<Attribute> attributes;

    public ASecondary(String identifier) {
        this.identifier = identifier;
        this.attributes = new ArrayList<>();
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public void setAllAttributes() {
        for (String attribute : Entities.Primary.Person.ATTRIBUTE_NAMES) {
            attributes.add(new Attribute(getIdentifier(), attribute));
        }
    }

    @Override
    public void removeAttribute(Attribute attribute) {
        this.attributes.remove(attribute);
    }

    @Override
    public void removeAttributes(List<Attribute> attribute) {
        this.attributes.removeAll(attribute);
    }

    @Override
    public void addAttribute(Attribute attribute) {
        attributes.add(attribute);
    }

    @Override
    public void addAttributes(List<Attribute> attributes) {
        this.attributes.addAll(attributes);
    }
}
