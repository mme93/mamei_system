package mamei.de.mdv.model;

import mamei.de.mdv.system.module.ESystem;
import mamei.de.mdv.system.module.ESystemCommand;
import mamei.de.mdv.system.module.SystemAction;
import mamei.de.mdv.system.module.SystemContext;

public class MDVAction {

    private String code;
    private ESystem system;
    private String systemName;
    private boolean defaultAction;
    private SystemContext context;

    public MDVAction(String code, ESystem system, String systemName, boolean defaultAction, SystemContext context) {
        this.code = code;
        this.system = system;
        this.systemName = systemName;
        this.defaultAction = defaultAction;
        this.context = context;
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

    public SystemAction getSystemAction(ESystemCommand command) {
        return new SystemAction(context, command);
    }
}
