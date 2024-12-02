package mamei.de.mdv;

import mamei.de.mdv.model.MDVAction;
import mamei.de.mdv.model.MDVResult;
import mamei.de.mdv.system.ISystem;
import mamei.de.mdv.system.model.SystemContent;

import java.util.List;

public interface IMDV {
    List<String> getLoadedSystemNames();

    void addSystem(SystemContent system);

    ISystem getSystemByName(String systemName);

    MDVResult action(MDVAction action);
}
