package mamei.de.mdv.system.data.entities.primary;

import mamei.de.mdv.system.data.entities.attribute.Attribute;
import mamei.de.mdv.system.data.entities.secondary.ISecondary;

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
