package mamei.de.mdv.datasets;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DataSet {

    private List<Data> entities = new ArrayList<>();

    public void addEntity(Data entity) {
        entities.add(entity);
    }

    public Optional<Data> getEntityByIdentifier() {
        return null;
        //return  entities.stream().findAny().filter(entity->)
    }

    public void clearEntities() {
        entities.clear();
    }

}
