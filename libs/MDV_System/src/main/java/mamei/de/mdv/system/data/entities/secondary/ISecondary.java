package mamei.de.mdv.system.data.entities.secondary;

import mamei.de.mdv.system.data.entities.attribute.Attribute;

import java.util.List;

public interface ISecondary {
    String getIdentifier();
    void setAllAttributes();
    void removeAttribute(Attribute attribute);
    void removeAttributes(List<Attribute> attribute);
    void addAttribute(Attribute attribute);
    void addAttributes(List<Attribute> attributes);
}
