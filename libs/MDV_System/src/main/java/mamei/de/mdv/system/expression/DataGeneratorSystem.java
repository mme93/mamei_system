package mamei.de.mdv.system.expression;

import mamei.de.mdv.system.System;

import java.util.Optional;

public class DataGeneratorSystem extends System {

    public DataGeneratorSystem() {
        super(DATA_GENERATOR_SYSTEM);
    }

    @Override
    public void action(String action) {
        java.lang.System.out.println("asfsdfgsdg");
    }

    @Override
    public Optional<Object> getMappedSystem() {
        return Optional.of(this);
    }
}
