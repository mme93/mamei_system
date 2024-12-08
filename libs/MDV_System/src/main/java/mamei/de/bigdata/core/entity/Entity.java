package mamei.de.bigdata.core.entity;

import mamei.de.bigdata.core.entity.attribute.EntityAttribute;

import java.util.List;
import java.util.Optional;

public abstract class Entity {

    private final String identifier;


    public Entity(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    public abstract List<EntityAttribute> getAttributes();

    public abstract Optional<EntityAttribute> getAttributeByName(String name);

}
