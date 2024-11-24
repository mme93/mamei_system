package mamei.de.mdv;

import mamei.de.mdv.system.ISystem;

public interface IMDV {

    Object getCastedSystem(String name);

    Object getCustomizedSystem(String name);

    ISystem getSystem(String name);
}
