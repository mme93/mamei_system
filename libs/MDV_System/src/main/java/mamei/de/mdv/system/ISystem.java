package mamei.de.mdv.system;

import mamei.de.mdv.action.MDVAction;
import mamei.de.mdv.model.MDVResult;
import mamei.de.mdv.system.context.ISystemContext;
import mamei.de.mdv.system.model.ESystem;

import java.util.Optional;

public interface ISystem {

    MDVResult action(MDVAction action);

    String getSystemName();

    ESystem getSystemTyp();

    Optional<Object> getMappedSystem();

}
