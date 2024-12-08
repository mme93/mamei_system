package mamei.de.mdv.system.context.generator;

import mamei.de.mdv.system.context.ISystemContext;
import mamei.de.bigdata.core.entity.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GeneratorContext implements ISystemContext {

    private List<Entity> entities = new ArrayList<>();
    private int amount;

    public GeneratorContext(int amount) {
        Objects.requireNonNull(amount);
        this.amount = amount;
    }

    public GeneratorContext(List<Entity> entities, int amount) {
        this.entities = entities;
        this.amount = amount;
    }

    public void addAllEntities(List<Entity> entities) {
        this.entities.addAll(entities);
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void clear() {
        entities.clear();
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}
