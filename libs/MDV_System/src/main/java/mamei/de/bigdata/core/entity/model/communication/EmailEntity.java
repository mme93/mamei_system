package mamei.de.bigdata.core.entity.model.communication;

import mamei.de.bigdata.core.entity.Entity;
import mamei.de.bigdata.core.entity.attribute.EntityAttribute;
import mamei.de.bigdata.core.entity.model.EntityModel;

import java.util.List;
import java.util.Optional;


public class EmailEntity extends Entity {

    private List<String> attributeNames = EntityModel.Secondary.Communication.Email.ATTRIBUTE_NAMES;
    private List<EntityAttribute> attributes = EntityModel.Secondary.Communication.Email.ENTITY_ATTRIBUTE_NAMES;

    public EmailEntity() {
        super(EntityModel.Secondary.Communication.Email.IDENTIFIER);
    }


    @Override
    public List<EntityAttribute> getAttributes() {
        return attributes;
    }

    @Override
    public Optional<EntityAttribute> getAttributeByName(String name) {
        return attributes.stream().findAny().filter(attribute -> attribute.getAttributeName().equals(name));
    }
}
