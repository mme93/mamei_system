package mamei.de.mdv.datasets;

import mamei.de.mdv.entity.Entity;
import mamei.de.mdv.entity.attribute.Attribute;
import mamei.de.mdv.entity.primary.Primary;
import mamei.de.mdv.entity.secondary.Secondary;
import mamei.de.mdv.exception.EntityClassCastException;

import java.util.ArrayList;
import java.util.List;

public class DataTable {

    private String name;
    private List<Attribute> attributes = new ArrayList<>();

    public DataTable(String name) {
        this.name = name;
    }

    public void loadTableContent(Entity entity) {
        if (entity instanceof Primary) {
            Primary primary = (Primary) entity;


        } else if (entity instanceof Secondary) {
            Secondary secondary = (Secondary) entity;
        }
        throw new EntityClassCastException("Can´t cast entity");
    }


}
