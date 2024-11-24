package mamei.de.mdv.system.module;

public class SystemIdentifier {

    private ESystem system;
    private String systemName;
    private boolean defaultAction;

    public SystemIdentifier(ESystem system, String systemName, boolean defaultAction) {
        this.system = system;
        this.systemName = systemName;
        this.defaultAction = defaultAction;
    }

    public ESystem getSystem() {
        return system;
    }

    public String getSystemName() {
        return systemName;
    }

    public boolean isDefaultAction() {
        return defaultAction;
    }
}
