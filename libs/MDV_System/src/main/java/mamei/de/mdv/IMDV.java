package mamei.de.mdv;

import mamei.de.mdv.model.MDVAction;

import java.util.List;

public interface IMDV {
    List<String> getLoadedSystemNames();

    void action(MDVAction action);
}
