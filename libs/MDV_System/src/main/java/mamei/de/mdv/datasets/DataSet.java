package mamei.de.mdv.datasets;

import mamei.de.mdv.entity.attribute.Attribute;

import java.util.ArrayList;
import java.util.List;

public class DataSet implements IDataSet {

    private final List<Data> dataSet;

    public DataSet() {
        this.dataSet = new ArrayList<>();
    }

    @Override
    public String asJSON() {
        return "";
    }

    @Override
    public String asDataList() {
        return "";
    }

    @Override
    public void addAttributeToEntity(Attribute attribute) {

    }

    @Override
    public void removeAttributeFromEntity(Attribute attribute) {

    }

}
