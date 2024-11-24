package mamei.de.mdv.system.data.set;

import mamei.de.mdv.system.data.IData;

import java.util.List;

public interface IDataSet {

    void addData(IData data);

    void addData(List<IData> dataList);

    void clear();

    boolean isValid();

    boolean isEmpty();

    int getAmount();

    String getName();

    List<IData> getDataList();
}
