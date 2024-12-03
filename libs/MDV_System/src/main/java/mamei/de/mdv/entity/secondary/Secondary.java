package mamei.de.mdv.entity.secondary;

import mamei.de.mdv.entity.Entity;
import mamei.de.mdv.entity.attribute.Attribute;

import java.util.Map;

public abstract class Secondary extends Entity {


    public Secondary(String identifier) {
        super(identifier);
    }

    public abstract void setDefaultAttributes();

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
