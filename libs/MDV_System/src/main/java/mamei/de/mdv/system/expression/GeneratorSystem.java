package mamei.de.mdv.system.expression;

import mamei.de.mdv.model.MDVResult;
import mamei.de.mdv.system.module.ESystem;
import mamei.de.mdv.system.System;
import mamei.de.mdv.system.module.SystemAction;

import java.util.Optional;

public class GeneratorSystem extends System {

    public GeneratorSystem(String name) {
        super(name);
    }

    @Override
    public MDVResult action(SystemAction action) {

        return new MDVResult();
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
