package mamei.de.datagenie.core.entity.models.communication;

import mamei.de.datagenie.core.entity.Entity;
import mamei.de.datagenie.core.attribute.Attribute;
import mamei.de.datagenie.core.entity.models.EEntityCategory;
import mamei.de.datagenie.core.entity.models.EEntityTyp;
import mamei.de.datagenie.core.entity.models.EntityModel;

import java.util.List;

import static java.util.Arrays.asList;

public class Handy extends Entity {

    public static final EEntityCategory category = EEntityCategory.COMMUNICATION;
    public static final EEntityTyp typ = EEntityTyp.HANDY;

    public static final String PROVIDER = "PROVIDER";
    public static final String NUMBER = "NUMBER";

    public static Attribute ATTRIBUTE_PROVIDER = new Attribute(EEntityCategory.COMMUNICATION, EEntityTyp.HANDY, PROVIDER);
    public static Attribute ATTRIBUTE_NUMBER = new Attribute(EEntityCategory.COMMUNICATION, EEntityTyp.HANDY, NUMBER);
    public static List<Attribute> ATTRIBUTES = asList(ATTRIBUTE_PROVIDER, ATTRIBUTE_NUMBER);

    public Handy(boolean withDefaultValues) {
        super(EntityModel.Communication.Mobile.IDENTIFIER, category, typ);
        if (withDefaultValues) {
            setDefaultAttributes();
        }
    }


    @Override
    public void setDefaultAttributes() {
        for (String attribute : EntityModel.Communication.Mobile.ATTRIBUTE_NAMES) {
            addAttribute(new Attribute(EEntityCategory.COMMUNICATION, EEntityTyp.HANDY, attribute));
        }
    }

    @Override
    public void addAttributes(List<Attribute> attributes) {
        attributes.forEach(attribute -> addAttribute(attribute));
    }

    @Override
    public void addAttribute(Attribute attribute) {
        if(hasTyp(attribute.getTyp())){
            super.addAttribute(attribute);
        }

    }
}
