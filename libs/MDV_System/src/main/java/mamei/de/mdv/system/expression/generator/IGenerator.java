package mamei.de.mdv.system.expression.generator;

import mamei.de.mdv.datasets.DataSet;
import mamei.de.mdv.system.context.ISystemContext;
import mamei.de.mdv.datasets.IData;

public interface IGenerator {

    DataSet loadDataSet(IData data);

    DataSet loadFromContext();

    void setContext(ISystemContext context);

    ISystemContext getContext();
}
