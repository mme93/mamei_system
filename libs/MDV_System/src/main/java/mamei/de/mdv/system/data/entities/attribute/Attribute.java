package mamei.de.mdv.system.data.entities.attribute;

public class Attribute {

    private final String attributeIdentifier;
    private final String attributeName;

    public Attribute(String attributeIdentifier, String attributeName) {
        this.attributeIdentifier = attributeIdentifier;
        this.attributeName = attributeName;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public String getAttributeIdentifier() {
        return attributeIdentifier;
    }
}
