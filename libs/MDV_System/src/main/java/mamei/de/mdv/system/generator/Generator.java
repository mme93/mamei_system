package mamei.de.mdv.system.generator;

import mamei.de.mdv.system.data.IData;
import mamei.de.mdv.system.data.set.IDataSet;

import java.util.List;

public abstract class Generator implements IGenerator {

    public abstract IDataSet generate(int amount);

    @Override
    public IDataSet loadDataSet(IData data) {
        return null;
    }

    protected boolean validateGeneratedData(List<IData> dataList) {
        if (dataList == null || dataList.isEmpty()) {
            throw new IllegalArgumentException("Generated data list is empty");
        }
        return true;
    }
}
