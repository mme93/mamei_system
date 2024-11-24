package mamei.de.mdv;

import mamei.de.mdv.exception.MDVActionErrorException;
import mamei.de.mdv.model.MDVAction;
import mamei.de.mdv.model.MDVModules;
import mamei.de.mdv.model.MDVResult;
import mamei.de.mdv.system.exception.NoSystemFoundException;
import mamei.de.mdv.system.module.ESystem;
import mamei.de.mdv.system.ISystem;
import mamei.de.mdv.system.expression.GeneratorSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class MDV implements IMDV {

    private MDVModules modules;

    private MDV(List<ISystem> customizedSystem, List<ESystem> systems) {
        this.modules = new MDVModules(systems);
        loadModules(customizedSystem);
    }

    private void loadModules(List<ISystem> customizedSystem) {
        Objects.requireNonNull(modules);
        modules.loadSystem(customizedSystem);
    }

    @Override
    public List<String> getLoadedSystemNames() {
        Objects.requireNonNull(modules);
        return modules.getLoadedSystemNames();
    }

    @Override
    public void addSystem(ISystem system) {
        modules.addSystem(system);
    }

    @Override
    public MDVResult action(MDVAction action) {

        switch (action.getIdentifier().getSystem()) {
            case GENERATOR:
                GeneratorSystem generator = modules.getGeneratorSystemByAction(action);
                return generator.action(action.getAction());
            default:
                throw new NoSystemFoundException(
                        String.format("Can´t execute action for system %s.", action.getIdentifier().getSystem())
                );
        }
    }

    public static MDVBuilder builder() {
        return new MDVBuilder();
    }

    public static class MDVBuilder {

        List<ISystem> customizedSystem = new ArrayList<>();
        List<ESystem> systems = new ArrayList<>();

        public MDVBuilder withCustomizedSystem(ISystem system) {
            customizedSystem.add(system);
            return this;
        }

        public MDVBuilder withGenerator() {
            systems.add(ESystem.GENERATOR);
            return this;
        }

        public MDV build() {
            return new MDV(customizedSystem, systems);
        }
    }
}
