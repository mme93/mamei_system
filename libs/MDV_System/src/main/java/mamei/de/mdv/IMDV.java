package mamei.de.mdv;

import mamei.de.mdv.model.MDVAction;
import mamei.de.mdv.system.ISystem;
import mamei.de.mdv.system.module.ESystemCommand;

import java.util.List;

public interface IMDV {
    List<String> getLoadedSystemNames();

    void addSystem(ISystem system);

    void action(MDVAction action, ESystemCommand command);
}
