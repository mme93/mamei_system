package mamei.de.datagenie.system.entity.builder;

import mamei.de.datagenie.core.entity.Entity;
import mamei.de.datagenie.core.entity.models.EEntityTyp;
import mamei.de.datagenie.system.entity.factory.IEntityFactory;
import mamei.de.datagenie.system.entity.factory.communication.CommunicationFactory;

import java.util.ArrayList;
import java.util.List;

public class EntityBuilder {

    public List<Entity> entities;

    private EntityBuilder(List<Entity> entities) {
        this.entities = entities;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public static EntityInitializationBuilder builder() {
        return new EntityInitializationBuilder();
    }


    public static class EntityInitializationBuilder {
        private CommunicationFactory communicationFactory;
        private List<IEntityFactory> iEntityFactories = new ArrayList<>();

        private EntityInitializationBuilder() {
            communicationFactory = new CommunicationFactory();
        }

        public EntityInitializationBuilder withCommunication(EEntityTyp typ) {
            createCommunication(typ);
            return this;
        }

        public EntityInitializationBuilder withCommunications(List<EEntityTyp> typ) {
            typ.forEach(entityTyp -> createCommunication(entityTyp));
            return this;
        }

        public EntityInitializationBuilder addEntityFactory(IEntityFactory factory) {
            iEntityFactories.add(factory);
            return this;
        }

        private void createCommunication(EEntityTyp typ) {
            switch (typ) {
                case HANDY, EMAIL -> communicationFactory.addEntity(typ);
            }
        }

        public EntityBuilder build() {
            return new EntityBuilder(iEntityFactories.get(0).getEntities());
        }

    }

}
