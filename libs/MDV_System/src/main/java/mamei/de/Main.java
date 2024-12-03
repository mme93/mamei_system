package mamei.de;


import mamei.de.mdv.MDV;
import mamei.de.mdv.action.MDVActionFactory;
import mamei.de.mdv.entity.factory.PrimaryFactory;
import mamei.de.mdv.entity.secondary.communication.Email;
import mamei.de.mdv.action.MDVAction;
import mamei.de.mdv.system.context.generator.GeneratorContext;
import mamei.de.mdv.entity.primary.person.Person;
import mamei.de.mdv.system.model.ESystemCommand;
import mamei.de.mdv.system.model.SystemIdentifier;
import mamei.de.mdv.system.model.SystemIdentifierRegistry;

import static java.util.Arrays.asList;

public class Main {

    static MDV mdv = MDV.builder().withGenerator().build();

    public static void main(String[] args) {
        test2();
    }

    public static void test2() {
        MDVAction action = MDVActionFactory.createAction(ESystemCommand.GENERATE, new GeneratorContext(asList(PrimaryFactory.generatePerson()), 10));
        mdv.action(action);
    }


    public static void test1() {

    }

    public static MDVAction generateGeneratorAction(SystemIdentifier identifier, ESystemCommand eCommand) {

        Email email = new Email();
        email.setDefaultAttributes();
        Person person = new Person();
        person.setDefaultAttributes();
        person.addSecondary(email);
        GeneratorContext context = new GeneratorContext(asList(person), 5);

        return new MDVAction(identifier, eCommand, context);
    }
}