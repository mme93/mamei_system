package mamei.de.mdv.system.expression;

import mamei.de.mdv.system.ESystem;
import mamei.de.mdv.system.System;

import java.util.Optional;

public class GeneratorSystem extends System {

    public GeneratorSystem() {
        super(GENERATOR_SYSTEM);
    }

    @Override
    public void action(String action) {

    }

    @Override
    public ESystem getSystemTyp() {
        return ESystem.GENERATOR;
    }

    @Override
    public Optional<Object> getMappedSystem() {
        return Optional.of(this);
    }



}
