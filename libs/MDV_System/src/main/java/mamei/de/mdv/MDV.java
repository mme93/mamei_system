package mamei.de.mdv;

import mamei.de.mdv.system.ISystem;
import mamei.de.mdv.system.exception.NoSystemFoundException;
import mamei.de.mdv.system.expression.DataGeneratorSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static mamei.de.mdv.system.System.*;


public class MDV implements IMDV {

    private List<ISystem> customizedSystem = new ArrayList<>();

    private MDV() {
    }

    @Override
    public Object getCastedSystem(String name) {
        switch (name) {
            case DATA_GENERATOR_SYSTEM:
                return new DataGeneratorSystem();
            default:
                throw new NoSystemFoundException(String.format("No system found by name: %s.", name));
        }
    }

    @Override
    public Object getCustomizedSystem(String name) {
        customizedSystem.stream().filter(iSystem -> {
            Optional<Object> optSystem = iSystem.getMappedSystem();
            return false;
        });
        return null;
    }

    @Override
    public ISystem getSystem(String name) {
        ISystem system;
        switch (name) {
            case DATA_GENERATOR_SYSTEM:
                system = new DataGeneratorSystem();
                break;
            default:
                throw new NoSystemFoundException(String.format("No system found by name: %s.", name));
        }
        return system;
    }

    public static MDVBuilder builder() {
        return new MDVBuilder();
    }

    public static class MDVBuilder {

        List<ISystem> customizedSystem = new ArrayList<>();
        List<String> systemNames = new ArrayList<>();

        public MDVBuilder addCustomizedSystem(ISystem system) {
            customizedSystem.add(system);
            return this;
        }

        public MDVBuilder addSystem(String systemName) {
            if (!SYSTEM_NAMES.contains(systemName)) {
                throw new NoSystemFoundException(String.format("No system found by name: %s", systemName));
            }
            systemNames.add(systemName);
            return this;
        }

        public MDV build() {
            return new MDV();
        }
    }
}
