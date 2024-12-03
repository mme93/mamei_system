package mamei.de.mdv.entity.secondary.communication;

import mamei.de.mdv.entity.Entities;
import mamei.de.mdv.entity.attribute.Attribute;
import mamei.de.mdv.entity.secondary.Secondary;

import java.util.Map;

public class Email extends Secondary {

    public Email() {
        super(Entities.Secondary.Communication.Email.IDENTIFIER);
    }

    public void setEmailDetails() {

        setDefaultAttributes();
    }

    @Override
    public void setDefaultAttributes() {
        for (String attribute : Entities.Secondary.Communication.Email.ATTRIBUTE_NAMES) {
            addAttribute(attribute);
        }
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
