package mamei.de.mdv.system.context.generator;

import mamei.de.mdv.system.context.ISystemContext;
import mamei.de.mdv.system.data.entities.Entity;

import java.util.ArrayList;
import java.util.List;

public class GeneratorContext implements ISystemContext {

    private List<Entity> entities = new ArrayList<>();

    public void addAllEntities(List<Entity> entities) {
        this.entities.addAll(entities);
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public List<Entity> getEntities() {
        return entities;
    }

    @Override
    public ISystemContext getContext() {
        return this;
    }
}
