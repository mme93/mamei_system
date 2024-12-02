package mamei.de.mdv;

import mamei.de.mdv.model.MDVAction;
import mamei.de.mdv.model.MDVModules;
import mamei.de.mdv.model.MDVResult;
import mamei.de.mdv.system.module.ESystem;
import mamei.de.mdv.system.ISystem;
import mamei.de.mdv.system.module.SystemContent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class MDV implements IMDV {

    private MDVModules modules;

    private MDV(List<ESystem> systems) {
        this.modules = new MDVModules(systems);
    }

    @Override
    public List<String> getLoadedSystemNames() {
        Objects.requireNonNull(modules);
        return modules.getLoadedSystemNames();
    }

    @Override
    public void addSystem(SystemContent system) {
        modules.addSystem(system);
    }

    @Override
    public ISystem getSystemByName(String systemName) {
        return modules.getSystemByName(systemName);
    }

    @Override
    public MDVResult action(MDVAction action) {
        return modules.executeAction(action);
    }

    public static MDVBuilder builder() {
        return new MDVBuilder();
    }

    public static class MDVBuilder {

        List<ESystem> systems = new ArrayList<>();

        public MDVBuilder withGenerator() {
            systems.add(ESystem.GENERATOR);
            return this;
        }

        public MDV build() {
            return new MDV(systems);
        }
    }
}
