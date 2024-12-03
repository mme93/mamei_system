package mamei.de.mdv.entity.primary.person;

import mamei.de.mdv.entity.attribute.Attribute;
import mamei.de.mdv.entity.primary.Primary;
import mamei.de.mdv.entity.secondary.Secondary;

import java.util.List;

import static mamei.de.mdv.entity.Entities.Primary.Person.*;

public class Person extends Primary {

    public Person() {
        super(IDENTIFIER);
        setDefaultAttributes();
    }

    @Override
    public void setDefaultAttributes() {
        for (String attribute : ATTRIBUTE_NAMES) {
            addAttribute(new Attribute(getIdentifier(),attribute));
        }
    }

    @Override
    public void addSecondary(Secondary secondary) {
        super.addSecondary(secondary);
    }

    @Override
    public List<Secondary> getSecondaries() {
        return super.getSecondaries();
    }

    @Override
    public String getIdentifier() {
        return super.getIdentifier();
    }

    @Override
    public List<Attribute> getAttributes() {
        return super.getAttributes();
    }

    @Override
    public void addAttribute(Attribute attribute) {
        super.addAttribute(attribute);
    }

    @Override
    public void removeAttribute(Attribute attribute) {
        super.removeAttribute(attribute);
    }

    @Override
    public void clearAttributes() {
        super.clearAttributes();
    }
}