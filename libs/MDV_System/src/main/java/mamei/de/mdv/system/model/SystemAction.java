package mamei.de.mdv.system.model;

import mamei.de.mdv.system.context.ISystemContext;

public class SystemAction {

    private ISystemContext context;
    private ESystemCommand command;

    public SystemAction(ISystemContext context, ESystemCommand command) {
        this.context = context;
        this.command = command;
    }

    public ISystemContext getContext() {
        return context;
    }

    public ESystemCommand getCommand() {
        return command;
    }
}
