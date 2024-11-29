package mamei.de;


import mamei.de.mdv.MDV;
import mamei.de.mdv.model.MDVAction;
import mamei.de.mdv.model.MDVResult;
import mamei.de.mdv.system.context.generator.GeneratorContext;
import mamei.de.mdv.system.expression.GeneratorSystem;
import mamei.de.mdv.system.module.*;

import static mamei.de.mdv.system.System.GENERATOR_SYSTEM;

public class Main {

    static MDV mdv = MDV.builder().withGenerator().build();

    public static void main(String[] args) {
        test2();

    }

    public static void test2() {
        GeneratorSystem generatorSystem = (GeneratorSystem) mdv.getSystemByName(GENERATOR_SYSTEM);

    }

    public static void test1() {
        SystemIdentifier identifier = new SystemIdentifier(ESystem.GENERATOR, GENERATOR_SYSTEM, true);
        GeneratorContext context = new GeneratorContext();
        context.addEntity(null);
        SystemAction action = new SystemAction(context, ESystemCommand.GENERATE);
        MDVResult result = mdv.action(new MDVAction(identifier, action));
    }
}