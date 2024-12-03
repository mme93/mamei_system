package mamei.de.mdv.action;

import mamei.de.mdv.system.context.generator.GeneratorContext;
import mamei.de.mdv.system.model.ESystemCommand;
import mamei.de.mdv.system.model.SystemIdentifierRegistry;

public class MDVActionFactory {

    public static MDVAction createAction(ESystemCommand command, GeneratorContext context) {
        return new MDVAction(SystemIdentifierRegistry.GENERATOR_IDENTIFIER, command, context);
    }

}
