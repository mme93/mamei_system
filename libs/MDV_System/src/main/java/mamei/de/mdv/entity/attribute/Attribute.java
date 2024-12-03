package mamei.de.mdv.entity.attribute;

import java.util.Objects;

public class Attribute {

    private final String attributeIdentifier;
    private final String attributeName;

    public Attribute(String attributeIdentifier, String attributeName) {
        Objects.requireNonNull(attributeIdentifier);
        Objects.requireNonNull(attributeName);

        this.attributeIdentifier = attributeIdentifier;
        this.attributeName = attributeName;
    }

    public String getAttributeIdentifier() {
        return attributeIdentifier;
    }

    public String getAttributeName() {
        return attributeName;
    }

    @Override
    public String toString() {
        return String.format("Attribute{identifier='%s', name='%s'}",
                attributeIdentifier, attributeName);
    }

}
