package mamei.de;


import mamei.de.mdv.MDV;
import mamei.de.mdv.model.MDVAction;
import mamei.de.mdv.model.MDVResult;
import mamei.de.mdv.system.context.generator.GeneratorContext;
import mamei.de.mdv.system.data.entities.person.Person;
import mamei.de.mdv.system.module.*;

import static mamei.de.mdv.system.System.GENERATOR_SYSTEM;

public class Main {

    static MDV mdv = MDV.builder().withGenerator().build();

    public static void main(String[] args) {
        test1();

    }

    public static void test1() {
        SystemIdentifier identifier = new SystemIdentifier(ESystem.GENERATOR, GENERATOR_SYSTEM, true);
        GeneratorContext context = new GeneratorContext();
        context.addEntity(new Person());
        SystemAction action  = new SystemAction(context, ESystemCommand.GENERATE);
        MDVResult result = mdv.action(new MDVAction(identifier,action));
    }
}