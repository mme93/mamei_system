package mamei.de.mdv.system;

import java.util.Optional;

public interface ISystem {

    void action(String action);

    String getSystemName();

    ESystem getSystemTyp();

    Optional<Object> getMappedSystem();

}
