package mamei.de.datagenie.system.entity.factory;

import mamei.de.datagenie.core.entity.Entity;
import mamei.de.datagenie.core.entity.models.EEntityTyp;

import java.util.List;

public interface IEntityFactory {

    List<Entity> getEntities();

    void addEntity(Entity entity);

    void addEntity(EEntityTyp typ);

    void addEntities(List<Entity> entities);

    void removeEntityByTyp(EEntityTyp typ);

    void removeEntityByTyp(List<EEntityTyp> typ);

    void clear();
}
