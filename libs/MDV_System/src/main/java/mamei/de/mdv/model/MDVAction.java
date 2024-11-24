package mamei.de.mdv.model;

import mamei.de.mdv.system.module.SystemAction;
import mamei.de.mdv.system.module.SystemIdentifier;

public class MDVAction {

    private SystemIdentifier identifier;
    private SystemAction action;

    public MDVAction(SystemIdentifier identifier, SystemAction action) {
        this.identifier = identifier;
        this.action = action;
    }

    public SystemIdentifier getIdentifier() {
        return identifier;
    }

    public SystemAction getAction() {
        return action;
    }
}
