package mamei.de.mdv.model;

import mamei.de.mdv.action.MDVAction;
import mamei.de.mdv.system.ISystem;
import mamei.de.mdv.exception.NoSystemFoundException;
import mamei.de.mdv.system.model.ESystem;
import mamei.de.mdv.system.expression.GeneratorSystem;
import mamei.de.mdv.system.model.SystemContent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static mamei.de.mdv.system.System.GENERATOR_SYSTEM;

public class MDVModules {


    List<SystemContent> systemContents = new ArrayList<>();

    public MDVModules(List<ESystem> system) {
        loadSystem(system);
    }

    public ISystem getSystemByName(String name) {
        return systemContents.stream()
                .filter(content -> content.getName().equals(name))
                .map(SystemContent::getSystem)
                .findFirst()
                .orElseThrow(() -> new NoSystemFoundException(String.format("System with name %s not found.", name)));
    }

    public <T extends ISystem> T getSystem(Class<T> systemClass) {
        return systemContents.stream()
                .map(SystemContent::getSystem)
                .filter(systemClass::isInstance)
                .map(systemClass::cast)
                .findFirst()
                .orElseThrow(() -> new NoSystemFoundException(String.format("System of type %s not found", systemClass.getSimpleName())));
    }

    public MDVResult executeAction(MDVAction action) {
        SystemContent systemContent = systemContents.stream()
                .filter(content -> content.getIdentifier().equals(action.getIdentifier().getSystem()))
                .findFirst()
                .orElseThrow(() -> new NoSystemFoundException(
                        "System not found for action: " + action.getIdentifier().getSystem()));

        return systemContent.getSystem().action(action);
    }


    private void loadSystem(List<ESystem> systems) {
        systems.forEach(typ -> {
            if (systemContents.stream().noneMatch(content -> content.getIdentifier() == typ)) {
                switch (typ) {
                    case GENERATOR ->
                            systemContents.add(new SystemContent(new GeneratorSystem(GENERATOR_SYSTEM), typ, GENERATOR_SYSTEM));
                }
            }
        });
    }

    public void addSystem(SystemContent system) {
        Objects.requireNonNull(system);
        systemContents.add(system);
    }

    public void removeSystem(String name) {
        systemContents.removeIf(content -> content.getName().equals(name));
    }

    public List<String> getLoadedSystemNames() {
        return systemContents.stream().map(systemContent -> systemContent.getName()).toList();
    }

}
