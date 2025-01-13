package mamei.de.datagenie.system.entity.factory;

import mamei.de.datagenie.core.entity.Entity;
import mamei.de.datagenie.core.entity.models.EEntityTyp;

import java.util.List;

public abstract class EntityFactory implements IEntityFactory {

    private List<Entity> entities;

    public EntityFactory(List<Entity> entities) {
        this.entities = entities;
    }

    @Override
    public List<Entity> getEntities() {
        return entities;
    }

    @Override
    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    @Override
    public void addEntities(List<Entity> entities) {
        this.entities.addAll(entities);
    }

    @Override
    public void removeEntityByTyp(EEntityTyp typ) {
        entities = entities.stream().filter(entity -> !entity.hasTyp(typ)).toList();
    }

    @Override
    public void removeEntityByTyp(List<EEntityTyp> entityTyp) {
        entityTyp.forEach(typ -> removeEntityByTyp(typ));
    }

    @Override
    public void clear() {
        entities.clear();
    }
}
