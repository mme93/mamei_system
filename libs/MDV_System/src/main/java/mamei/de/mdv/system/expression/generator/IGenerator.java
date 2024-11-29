package mamei.de.mdv.system.expression.generator;

import mamei.de.mdv.system.context.ISystemContext;
import mamei.de.mdv.system.data.IData;
import mamei.de.mdv.system.data.set.IDataSet;

public interface IGenerator {

    IDataSet loadDataSet(IData data);

    IDataSet loadFromContext();

    void setContext(ISystemContext context);

    ISystemContext getContext();
}
