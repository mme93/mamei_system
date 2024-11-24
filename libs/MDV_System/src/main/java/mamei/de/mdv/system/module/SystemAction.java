package mamei.de.mdv.system.module;

public class SystemAction {

    private SystemContext context;
    private ESystemCommand command;

    public SystemAction(SystemContext context, ESystemCommand command) {
        this.context = context;
        this.command = command;
    }

    public SystemContext getContext() {
        return context;
    }

    public ESystemCommand getCommand() {
        return command;
    }
}
