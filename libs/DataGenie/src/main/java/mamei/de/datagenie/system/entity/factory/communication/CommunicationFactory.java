package mamei.de.datagenie.system.entity.factory.communication;

import mamei.de.datagenie.core.entity.Entity;
import mamei.de.datagenie.core.entity.models.EEntityTyp;
import mamei.de.datagenie.core.entity.models.communication.Email;
import mamei.de.datagenie.core.entity.models.communication.Handy;
import mamei.de.datagenie.core.exception.EntityIllegalArgumentException;
import mamei.de.datagenie.system.entity.factory.EntityFactory;

import java.util.List;

import static java.util.Arrays.asList;

public class CommunicationFactory extends EntityFactory {


    public CommunicationFactory() {
        super(asList());
    }

    public CommunicationFactory(List<Entity> entities) {
        super(entities);
    }

    @Override
    public void addEntity(Entity entity) {
        switch (entity.getTyp()) {
            case HANDY -> super.addEntity(createHandy());
            case EMAIL -> super.addEntity(createEmail());
            default ->
                    throw new EntityIllegalArgumentException(String.format("The type %s is not correct as communication", entity.getTyp()));
        }
    }



    @Override
    public void addEntity(EEntityTyp typ) {
        switch (typ) {
            case HANDY -> super.addEntity(createHandy());
            default ->
                    throw new EntityIllegalArgumentException(String.format("The type %s is not correct as communication", typ));
        }
    }

    private Entity createEmail() {
       return new Email(true);
    }

    private Entity createHandy() {
        return new Handy(true);
    }
}
