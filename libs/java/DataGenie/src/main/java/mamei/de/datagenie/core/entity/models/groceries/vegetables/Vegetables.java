package mamei.de.datagenie.core.entity.models.groceries.vegetables;

import mamei.de.datagenie.core.entity.Entity;
import mamei.de.datagenie.core.entity.models.EEntityCategory;
import mamei.de.datagenie.core.entity.models.EEntityTyp;

public abstract class Vegetables extends Entity {

    public Vegetables(String id, EEntityCategory category, EEntityTyp typ) {
        super(id, category, typ);
    }
}
