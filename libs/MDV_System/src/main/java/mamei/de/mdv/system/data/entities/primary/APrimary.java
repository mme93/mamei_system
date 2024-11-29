package mamei.de.mdv.system.data.entities.primary;

import mamei.de.mdv.system.data.entities.Entities;
import mamei.de.mdv.system.data.entities.attribute.Attribute;
import mamei.de.mdv.system.data.entities.secondary.ISecondary;

import java.util.ArrayList;
import java.util.List;

public abstract class APrimary implements IPrimary {

    private List<ISecondary> secondaries;
    private List<Attribute> attributes;

    private String identifier;

    public APrimary(String identifier) {
        this.identifier = identifier;
        secondaries = new ArrayList<>();
        attributes = new ArrayList<>();
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public List<ISecondary> getSecondaries() {
        return secondaries;
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
