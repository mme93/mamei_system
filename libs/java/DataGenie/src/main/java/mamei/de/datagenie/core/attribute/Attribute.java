package mamei.de.datagenie.core.attribute;

import mamei.de.datagenie.core.entity.models.EEntityCategory;
import mamei.de.datagenie.core.entity.models.EEntityTyp;

import java.util.Objects;
import java.util.Optional;

import static java.util.Optional.of;

public class Attribute {

    private EEntityCategory category;
    private EEntityTyp typ;
    private Optional<String> name;


    public Attribute(EEntityCategory category, EEntityTyp typ, String name) {
        Objects.requireNonNull(category);
        Objects.requireNonNull(typ);
        Objects.requireNonNull(name);
        this.category = category;
        this.typ = typ;
        this.name = of(name);
    }

    public EEntityTyp getTyp() {
        return typ;
    }

    public void setTyp(EEntityTyp typ) {
        this.typ = typ;
    }

    public String getName() {
        return name.orElse("No Name is init.");
    }

    public void setName(String name) {
        this.name = of(name);
    }
}
