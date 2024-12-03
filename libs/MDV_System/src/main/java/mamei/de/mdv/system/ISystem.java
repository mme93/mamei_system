package mamei.de.mdv.system;

import mamei.de.mdv.model.MDVAction;
import mamei.de.mdv.model.MDVResult;
import mamei.de.mdv.system.context.ISystemContext;
import mamei.de.mdv.system.context.generator.GeneratorContext;
import mamei.de.mdv.system.model.ESystem;

import java.util.Optional;

public interface ISystem {

    MDVResult action(MDVAction action);

    String getSystemName();

    ESystem getSystemTyp();

    Optional<Object> getMappedSystem();

    MDVResult generate(ISystemContext context);

}
