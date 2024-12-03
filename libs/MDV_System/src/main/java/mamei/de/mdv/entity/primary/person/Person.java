package mamei.de.mdv.entity.primary.person;

import mamei.de.mdv.entity.attribute.Attribute;
import mamei.de.mdv.entity.primary.Primary;
import mamei.de.mdv.entity.secondary.Secondary;

import java.util.List;
import java.util.Map;

import static mamei.de.mdv.entity.Entities.Primary.Person.*;

public class Person extends Primary {


    public Person() {
        super(IDENTIFIER);
        setDefaultAttributes();
    }

    @Override
    public void setDefaultAttributes() {
        for (String attribute : ATTRIBUTE_NAMES) {
            addAttribute(attribute);
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
    public Map<String, Attribute> getAttributes() {
        return super.getAttributes();
    }

    @Override
    public void addAttribute(String name) {
        super.addAttribute(name);
    }

    @Override
    public void removeAttribute(String name) {
        super.removeAttribute(name);
    }
}