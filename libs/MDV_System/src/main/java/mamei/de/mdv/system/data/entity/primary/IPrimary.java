package mamei.de.mdv.system.data.entity.primary;

import mamei.de.mdv.system.data.entity.attribute.Attribute;
import mamei.de.mdv.system.data.entity.secondary.ISecondary;

import java.util.List;

public interface IPrimary {
    String getIdentifier();
    List<ISecondary> getSecondaries();
    void setAllAttributes();
    void removeAttribute(Attribute attribute);
    void removeAttributes(List<Attribute> attribute);
    void addAttribute(Attribute attribute);
    void addAttributes(List<Attribute> attributes);
}
