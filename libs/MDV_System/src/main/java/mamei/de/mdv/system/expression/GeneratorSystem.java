package mamei.de.mdv.system.expression;

import mamei.de.mdv.entity.Entity;
import mamei.de.mdv.model.MDVAction;
import mamei.de.mdv.model.MDVResult;
import mamei.de.mdv.system.context.generator.GeneratorContext;
import mamei.de.mdv.system.model.ESystem;
import mamei.de.mdv.system.System;

import java.util.Optional;

public class GeneratorSystem extends System {

    public GeneratorSystem(String name) {
        super(name);
    }

    public MDVResult load(GeneratorContext context) {

        return null;
    }

    @Override
    public MDVResult action(MDVAction action) {
        if (!(action.getContext() instanceof GeneratorContext context)) {
            throw new IllegalArgumentException("Invalid context for GeneratorSystem");
        }
        switch (action.getCommand()) {
            case GENERATE -> {
                return generateEntities(context);
            }
            default -> throw new UnsupportedOperationException(
                    "Command not supported: " + action.getCommand());
        }
    }

    @Override
    public ESystem getSystemTyp() {
        return ESystem.GENERATOR;
    }

    @Override
    public Optional<Object> getMappedSystem() {
        return Optional.of(this);
    }

    private MDVResult generateEntities(GeneratorContext context) {
        for (int i = 0; i < context.getAmount(); i++) {
            for (Entity entity : context.getEntities()) {

            }
        }
        return new MDVResult("Generated entities.");
    }


}
