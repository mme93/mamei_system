package mamei.de.datagenie.core.entity.models;

import mamei.de.datagenie.core.entity.Entity;
import mamei.de.datagenie.core.exception.EntityIllegalArgumentException;

public class ModifiedEntity extends Entity {

    public static final EEntityTyp typ = EEntityTyp.MODIFIED;
    public static final EEntityCategory category = EEntityCategory.MODIFIED;

    public ModifiedEntity(String id) {
        super(id, category, typ);
    }


    @Override
    public void setDefaultAttributes() {
        throw new EntityIllegalArgumentException("ModifiedEntity have no default data that can be set.");
    }
}
