package mamei.de.mdv.system.model;

public class SystemIdentifier {

    private final ESystem system;
    private final String systemName;

    public SystemIdentifier(ESystem system, String systemName) {
        this.system = system;
        this.systemName = systemName;
    }

    public ESystem getSystem() {
        return system;
    }

    public String getSystemName() {
        return systemName;
    }
}
