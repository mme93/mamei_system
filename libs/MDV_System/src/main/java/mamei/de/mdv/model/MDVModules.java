package mamei.de.mdv.model;


import mamei.de.mdv.system.ESystem;
import mamei.de.mdv.system.ISystem;
import mamei.de.mdv.system.exception.NoSystemFoundException;
import mamei.de.mdv.system.expression.GeneratorSystem;

import java.util.ArrayList;
import java.util.List;

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
                    GeneratorSystem generatorSystem = new GeneratorSystem();
                    addSystem(generatorSystem);
                    systemNames.add(generatorSystem.getSystemName());
                    break;
            }
        }
    }

    public void addSystem(ISystem system) {

        if(!system.getMappedSystem().isPresent()){
           throw new NoSystemFoundException("No system exist");
        }

        switch (system.getSystemTyp()){
            case GENERATOR -> generatorSystems.add((GeneratorSystem) system.getMappedSystem().get());
        }
    }


    public List<String> getLoadedSystemNames() {
        return systemNames;
    }

}
