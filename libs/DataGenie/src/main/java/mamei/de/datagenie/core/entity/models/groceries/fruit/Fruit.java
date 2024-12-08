package mamei.de.datagenie.core.entity.models.groceries.fruit;

import mamei.de.datagenie.core.entity.Entity;
import mamei.de.datagenie.core.entity.models.EEntityCategory;
import mamei.de.datagenie.core.entity.models.EEntityTyp;

public abstract class Fruit extends Entity {

    public Fruit(String id, EEntityCategory category, EEntityTyp typ) {
        super(id, category, typ);
    }
}
