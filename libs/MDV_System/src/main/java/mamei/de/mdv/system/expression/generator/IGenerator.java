package mamei.de.mdv.system.expression.generator;

import mamei.de.mdv.datasets.DataSet;
import mamei.de.mdv.datasets.DataTable;
import mamei.de.mdv.entity.Entity;
import mamei.de.mdv.system.context.ISystemContext;

public interface IGenerator {

    DataTable loadTable(Entity entity);

    DataSet loadFromContext();

    void setContext(ISystemContext context);

    ISystemContext getContext();
}
