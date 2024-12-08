package mamei.de.mdv.system.expression.generator.expression.person;

import mamei.de.mdv.datasets.DataSet;
import mamei.de.mdv.datasets.DataTable;
import mamei.de.mdv.system.context.ISystemContext;
import mamei.de.mdv.system.context.generator.GeneratorContext;
import mamei.de.mdv.system.expression.generator.Generator;

public class PersonGenerator extends Generator {

    private GeneratorContext context;

    @Override
    public DataTable generate(int amount) {
        return null;
    }

    @Override
    public DataSet loadFromContext() {
        return null;
    }


    @Override
    protected boolean validateGeneratedData() {
        return false;
    }

    @Override
    public void setContext(ISystemContext context) {
        if (context != null) {
            this.context = (GeneratorContext) context;
            super.setContext(context);
        }
        throw new NullPointerException("Context is null");
    }

    @Override
    public ISystemContext getContext() {
        return context;
    }

}
