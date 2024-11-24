package mamei.de.mdv.model;

import mamei.de.mdv.system.ESystem;

public class MDVAction {

    private String code;
    private ESystem system;
    private String systemName;
    private boolean defaultAction;

    public MDVAction(String code, ESystem system, String systemName, boolean defaultAction) {
        this.code = code;
        this.system = system;
        this.systemName = systemName;
        this.defaultAction = defaultAction;
    }

    public String getCode() {
        return code;
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
