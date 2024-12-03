package mamei.de.mdv.entity.secondary.communication;

import mamei.de.mdv.entity.Entities;
import mamei.de.mdv.entity.attribute.Attribute;
import mamei.de.mdv.entity.secondary.Secondary;

import java.util.List;

public class Email extends Secondary {

    public Email() {
        super(Entities.Secondary.Communication.Email.IDENTIFIER);
        setDefaultAttributes();
    }

    @Override
    public void setDefaultAttributes() {
        for (String attribute : Entities.Secondary.Communication.Email.ATTRIBUTE_NAMES) {
            addAttribute(new Attribute(getIdentifier(), attribute));
        }
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
}
