package mamei.de.mdv.model;


import mamei.de.mdv.system.module.ESystem;
import mamei.de.mdv.system.ISystem;
import mamei.de.mdv.system.exception.NoSystemFoundException;
import mamei.de.mdv.system.expression.GeneratorSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static mamei.de.mdv.system.System.GENERATOR_SYSTEM;

public class MDVModules {
    private List<ESystem> system;
    private List<GeneratorSystem> generatorSystems = new ArrayList<>();
    private List<String> systemNames = new ArrayList<>();

    public MDVModules(List<ESystem> system) {
        this.system = system;
    }

    public void loadSystem(List<ISystem> customizedSystem) {
        customizedSystem.forEach(system -> {
            addSystem(system);
            systemNames.add(system.getSystemName());
        });
        for (ESystem typ : system) {
            switch (typ) {
                case GENERATOR:
                    GeneratorSystem generatorSystem = new GeneratorSystem(GENERATOR_SYSTEM);
                    addSystem(generatorSystem);
                    systemNames.add(generatorSystem.getSystemName());
                    break;
            }
        }
    }

    public void addSystem(ISystem system) {

        if (!system.getMappedSystem().isPresent()) {
            throw new NoSystemFoundException("No system exist");
        }

        switch (system.getSystemTyp()) {
            case GENERATOR -> generatorSystems.add((GeneratorSystem) system.getMappedSystem().get());
            default ->
                    throw new NoSystemFoundException(String.format("No system found by type %S.", system.getSystemTyp()));
        }
    }


    public List<String> getLoadedSystemNames() {
        return systemNames;
    }

    public GeneratorSystem getGeneratorSystemByAction(MDVAction mdvAction) {
        Optional<GeneratorSystem> system = generatorSystems.stream().filter(generator -> {
            if (mdvAction.getIdentifier().isDefaultAction()) {
                return generator.getSystemName().equals(GENERATOR_SYSTEM);
            } else {
                return generator.getSystemName().equals(mdvAction.getIdentifier().getSystemName());
            }
        }).findAny();

        if (system.isPresent()) {
            return system.get();
        }
        throw new NoSystemFoundException("No system found.");
    }


    public List<GeneratorSystem> getGeneratorSystems() {
        return generatorSystems;
    }
}
