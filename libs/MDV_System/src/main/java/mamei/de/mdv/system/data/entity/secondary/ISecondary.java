package mamei.de.mdv.system.data.entity.secondary;

import mamei.de.mdv.system.data.entity.attribute.Attribute;

import java.util.List;

public interface ISecondary {
    String getIdentifier();
    void setAllAttributes();
    void removeAttribute(Attribute attribute);
    void removeAttributes(List<Attribute> attribute);
    void addAttribute(Attribute attribute);
    void addAttributes(List<Attribute> attributes);
}
