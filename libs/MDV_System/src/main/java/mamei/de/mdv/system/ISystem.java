package mamei.de.mdv.system;

import mamei.de.mdv.model.MDVResult;
import mamei.de.mdv.system.module.ESystem;
import mamei.de.mdv.system.module.SystemAction;

import java.util.Optional;

public interface ISystem {

    MDVResult action(SystemAction action);

    String getSystemName();

    ESystem getSystemTyp();

    Optional<Object> getMappedSystem();

}
