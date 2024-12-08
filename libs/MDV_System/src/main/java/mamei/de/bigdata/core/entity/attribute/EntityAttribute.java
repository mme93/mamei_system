package mamei.de.bigdata.core.entity.attribute;

public class EntityAttribute {

    private String entityIdentifier;
    private String attributeName;

    public EntityAttribute(String entityIdentifier, String attributeName) {
        this.entityIdentifier = entityIdentifier;
        this.attributeName = attributeName;
    }

    public String getEntityIdentifier() {
        return entityIdentifier;
    }

    public void setEntityIdentifier(String entityIdentifier) {
        this.entityIdentifier = entityIdentifier;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }
}
