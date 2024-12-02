package mamei.de.mdv.entity.secondary;

import mamei.de.mdv.entity.Entity;

public abstract class Secondary extends Entity {


    public Secondary(String identifier) {
        super(identifier);
    }

    public abstract void setDefaultAttributes();

}
