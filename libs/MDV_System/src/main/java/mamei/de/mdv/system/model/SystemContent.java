package mamei.de.mdv.system.model;

import mamei.de.mdv.system.ISystem;

public class SystemContent {

    private ISystem system;
    private ESystem identifier;
    private String name;

    public SystemContent(ISystem system, ESystem identifier, String name) {
        this.system = system;
        this.identifier = identifier;
        this.name = name;
    }

    public ISystem getSystem() {
        return system;
    }

    public void setSystem(ISystem system) {
        this.system = system;
    }

    public ESystem getIdentifier() {
        return identifier;
    }

    public void setIdentifier(ESystem identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
