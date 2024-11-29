package mamei.de.mdv.system.expression.generator.expression.communication;

import mamei.de.mdv.system.context.ISystemContext;
import mamei.de.mdv.system.context.generator.GeneratorContext;
import mamei.de.mdv.system.data.IData;
import mamei.de.mdv.system.data.set.IDataSet;
import mamei.de.mdv.system.expression.generator.Generator;

import java.util.List;

public class CommunicationGenerator extends Generator {

    private GeneratorContext context;


    @Override
    public void setContext(ISystemContext context) {
        super.setContext(context);
        this.context= (GeneratorContext) context;
    }

    @Override
    public ISystemContext getContext() {
        return context;
    }

    @Override
    public IDataSet generate(int amount) {
        return null;
    }

    @Override
    public IDataSet loadFromContext() {
        return null;
    }

    @Override
    public IDataSet loadDataSet(IData data) {
        return null;
    }

    @Override
    protected boolean validateGeneratedData(List<IData> dataList) {
        return false;
    }


}
