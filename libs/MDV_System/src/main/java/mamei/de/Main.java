package mamei.de;


import mamei.de.mdv.MDV;
import mamei.de.mdv.entity.secondary.communication.Email;
import mamei.de.mdv.model.MDVAction;
import mamei.de.mdv.system.context.generator.GeneratorContext;
import mamei.de.mdv.entity.primary.person.Person;
import mamei.de.mdv.system.model.ESystem;
import mamei.de.mdv.system.model.ESystemCommand;
import mamei.de.mdv.system.model.SystemIdentifier;

import static java.util.Arrays.asList;

public class Main {

    static MDV mdv = MDV.builder().withGenerator().build();

    public static void main(String[] args) {
        test2();
    }

    public static void test2() {
        MDVAction action =generateGeneratorAction(ESystem.GENERATOR, ESystemCommand.GENERATE);
        mdv.action(action);
    }



    public static void test1() {

    }

    public static MDVAction generateGeneratorAction(ESystem eSystem, ESystemCommand eCommand) {
        SystemIdentifier identifier = new SystemIdentifier(eSystem, "GeneratorSystem");

        Email email= new Email();
        email.setDefaultAttributes();
        Person person = new Person();
        person.setDefaultAttributes();
        person.addSecondary(email);
        GeneratorContext context = new GeneratorContext(asList(person),5);

        return new MDVAction(identifier, eCommand, context);
    }
}