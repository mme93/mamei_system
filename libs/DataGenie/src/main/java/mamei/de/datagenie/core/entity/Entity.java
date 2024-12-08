package mamei.de.datagenie.core.entity;

import mamei.de.datagenie.core.attribute.Attribute;
import mamei.de.datagenie.core.entity.models.EEntityCategory;
import mamei.de.datagenie.core.entity.models.EEntityTyp;

import java.util.List;

public abstract class Entity {

    private String id;
    private List<Attribute> attributes;
    private EEntityCategory category;
    private EEntityTyp typ;

    public Entity(String id, EEntityCategory category, EEntityTyp typ) {
        this.id = id;
        this.category = category;
        this.typ = typ;
    }


    public void addAttributes(List<Attribute> attributes) {
        this.attributes.addAll(attributes);
    }

    public void addAttribute(Attribute attribute) {
        this.attributes.add(attribute);
    }

    public void deleteAttributes(List<Attribute> attributes) {
        this.attributes.remove(attributes);
    }

    public void deleteAttribute(Attribute attribute) {
        this.attributes.remove(attribute);
    }

    public void clearList() {
        this.attributes.clear();
    }

    public List<Attribute> getAttributes() {
        return this.attributes;
    }

    public String getId() {
        return id;
    }

    public EEntityCategory getCategory() {
        return category;
    }

    public EEntityTyp getTyp() {
        return typ;
    }

    public boolean hasTyp(EEntityTyp typ) {
        return this.typ.equals(typ);
    }

    public abstract void setDefaultAttributes();

    public boolean isCategory(EEntityCategory category) {
        return this.category.equals(category);
    }
}
