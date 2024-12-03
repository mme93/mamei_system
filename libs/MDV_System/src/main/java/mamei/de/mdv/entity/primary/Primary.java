package mamei.de.mdv.entity.primary;

import mamei.de.mdv.entity.Entity;
import mamei.de.mdv.entity.attribute.Attribute;
import mamei.de.mdv.entity.secondary.Secondary;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class Primary extends Entity {

    private final List<Secondary> secondaries;

    public Primary(String identifier) {
        super(identifier);
        this.secondaries = new ArrayList<>();
    }

    public void addSecondary(Secondary secondary) {
        secondaries.add(secondary);
    }

    public List<Secondary> getSecondaries() {
        return secondaries;
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
