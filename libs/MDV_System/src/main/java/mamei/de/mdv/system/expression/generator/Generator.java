package mamei.de.mdv.system.expression.generator;

import mamei.de.mdv.datasets.DataSet;
import mamei.de.mdv.system.context.ISystemContext;
import mamei.de.mdv.system.context.generator.GeneratorContext;
import mamei.de.mdv.datasets.IData;

import java.util.List;

public abstract class Generator implements IGenerator {

    private GeneratorContext context;

    public abstract DataSet generate(int amount);

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
    public abstract DataSet loadDataSet(IData data);

    protected abstract boolean validateGeneratedData(List<IData> dataList) ;

}
