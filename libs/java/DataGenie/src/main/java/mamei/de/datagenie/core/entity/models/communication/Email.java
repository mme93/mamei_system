package mamei.de.datagenie.core.entity.models.communication;

import mamei.de.datagenie.core.attribute.Attribute;
import mamei.de.datagenie.core.entity.Entity;
import mamei.de.datagenie.core.entity.models.EEntityCategory;
import mamei.de.datagenie.core.entity.models.EEntityTyp;
import mamei.de.datagenie.core.entity.models.EntityModel;

import java.util.List;

import static java.util.Arrays.asList;

public class Email extends Entity {

    public static final EEntityCategory category = EEntityCategory.COMMUNICATION;
    public static final EEntityTyp typ = EEntityTyp.EMAIL;

    public static final String PROVIDER = "PROVIDER";
    public static final String NAME = "NAME";

    public static Attribute ATTRIBUTE_PROVIDER = new Attribute(category, typ, PROVIDER);
    public static Attribute ATTRIBUTE_NAME = new Attribute(category, typ, NAME);
    public static List<Attribute> ATTRIBUTES = asList(ATTRIBUTE_PROVIDER, ATTRIBUTE_NAME);

    public Email(boolean withDefaultValues) {
        super(EntityModel.Communication.Email.IDENTIFIER, category, typ);
        if (withDefaultValues) {
            setDefaultAttributes();
        }
    }

    @Override
    public void setDefaultAttributes() {
        for (String attribute : EntityModel.Communication.Mobile.ATTRIBUTE_NAMES) {
            addAttribute(new Attribute(category, typ, attribute));
        }
    }
}
