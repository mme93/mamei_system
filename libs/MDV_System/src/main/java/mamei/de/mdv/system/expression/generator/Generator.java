package mamei.de.mdv.system.expression.generator;

import mamei.de.mdv.datasets.DataSet;
import mamei.de.mdv.datasets.DataTable;
import mamei.de.bigdata.core.entity.Entity;
import mamei.de.mdv.system.context.ISystemContext;
import mamei.de.mdv.system.context.generator.GeneratorContext;

public abstract class Generator implements IGenerator {

    private GeneratorContext context;

    public abstract DataTable generate(int amount);

    @Override
    public abstract DataSet loadFromContext();

    @Override
    public void setContext(ISystemContext context) {
        this.context = (GeneratorContext) context;
    }

    @Override
    public ISystemContext getContext() {
        return context;
    }

    @Override
    public DataTable loadTable(Entity entity) {
        return null;
    }

    protected abstract boolean validateGeneratedData() ;

}
