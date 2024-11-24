package mamei.de.mdv.system.data.set;

import mamei.de.mdv.system.data.IData;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DataSet implements IDataSet {

    private List<IData> dataList = new ArrayList<>();
    private int amount;
    private String name;

    public DataSet(int amount, String name) {
        this.amount = amount;
        this.name = name;
    }


    @Override
    public IDataSet generate(List<IData> dataList) {
        if (dataList.isEmpty()) {
            throw new NoDataFoundException("List with data is empty");
        }

        return null;
    }

    @Override
    public IDataSet generate() {
        if (dataList.isEmpty()) {
            throw new NoDataFoundException("List with data is empty");
        }

        return null;
    }

    @Override
    public void addData(IData data) {
        try {
            Objects.requireNonNull(data);
        } catch (Exception ex) {
            throw new NoDataFoundException("Data must not be null.", ex);
        }
    }

    @Override
    public void addData(List<IData> dataList) {
        if (dataList.isEmpty()) {
            throw new NoDataFoundException("List with data is empty.");
        }
        dataList.addAll(dataList);
    }

    @Override
    public void clear() {
        dataList.clear();
    }

    @Override
    public boolean isValid() {

        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
