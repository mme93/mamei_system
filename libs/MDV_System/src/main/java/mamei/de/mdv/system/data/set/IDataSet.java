package mamei.de.mdv.system.data.set;

import mamei.de.mdv.system.data.IData;

import java.util.List;

public interface IDataSet {

    IDataSet generate(List<IData> dataList);

    IDataSet generate();

    void addData(IData data);

    void addData(List<IData> dataList);

    void clear();

    boolean isValid();

    boolean isEmpty();
}
