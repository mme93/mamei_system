package mamei.de.mdv.system.data.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Entity {

    private List<String> properties = new ArrayList<>();
    private final String identifier;


    public Entity(String identifier) {
        Objects.requireNonNull(identifier);
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }
}
