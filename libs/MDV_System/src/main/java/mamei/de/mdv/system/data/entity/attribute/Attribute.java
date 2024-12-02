package mamei.de.mdv.system.data.entity.attribute;

import java.util.Objects;

public class Attribute {

    private final String attributeIdentifier;
    private final String attributeName;
    private Object value;

    public Attribute(String attributeIdentifier, String attributeName) {
        Objects.requireNonNull(attributeIdentifier, "Attribute Identifier darf nicht null sein");
        Objects.requireNonNull(attributeName, "Attribute Name darf nicht null sein");

        this.attributeIdentifier = attributeIdentifier;
        this.attributeName = attributeName;
    }

    public String getAttributeIdentifier() {
        return attributeIdentifier;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("Attribute{identifier='%s', name='%s', value=%s}",
                attributeIdentifier, attributeName, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attribute attribute = (Attribute) o;
        return attributeIdentifier.equals(attribute.attributeIdentifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attributeIdentifier);
    }
}
