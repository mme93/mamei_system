package mamei.de.mdv.system.data.entity.primary;

import mamei.de.mdv.system.data.entity.Entity;
import mamei.de.mdv.system.data.entity.secondary.Secondary;

import java.util.ArrayList;
import java.util.List;

public class Primary extends Entity {

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

    public void setDefaultAttributes() {
        // Setzt Standardattribute für die primäre Entität
    }
}