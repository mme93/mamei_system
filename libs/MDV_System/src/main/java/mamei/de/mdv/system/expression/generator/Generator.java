package mamei.de.mdv.system.expression.generator;

import mamei.de.mdv.system.context.ISystemContext;
import mamei.de.mdv.system.context.generator.GeneratorContext;
import mamei.de.mdv.system.data.IData;
import mamei.de.mdv.system.data.set.IDataSet;

import java.util.List;

public abstract class Generator implements IGenerator {

    private GeneratorContext context;

    public abstract IDataSet generate(int amount);

    @Override
    public abstract IDataSet loadFromContext();

    @Override
    public void setContext(ISystemContext context) {
        this.context = (GeneratorContext) context;
    }

    @Override
    public ISystemContext getContext() {
        return context;
    }

    @Override
    public abstract IDataSet loadDataSet(IData data);

    protected abstract boolean validateGeneratedData(List<IData> dataList) ;

}
