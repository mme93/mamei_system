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
        test3();
    }

    public static void test3() {
        MDVAction action =generateGeneratorAction(ESystem.GENERATOR, ESystemCommand.GENERATE);
        mdv.action(action);
    }

    public static void test2() {
        Person person = new Person();
        person.setFirstName("Max");
        person.setAttribute("lastName", "Mustermann");
        person.setAttribute("lastName", "Peter");
        person.setAttribute("age", 30);

        Email email = new Email();
        email.setDefaultAttributes();

        person.addSecondary(email);

        System.out.println("Person: " + person);
        System.out.println("Primäre Attribute: " + person.getAttributes());

        person.getSecondaries().forEach(secondary -> {
            System.out.println("Secondary: " + secondary.getIdentifier());
            System.out.println("Attributes: " + secondary.getAttributes());
        });
    }

    public static void test1() {

    }

    public static MDVAction generateGeneratorAction(ESystem eSystem, ESystemCommand eCommand) {
        SystemIdentifier identifier = new SystemIdentifier(eSystem, "GeneratorSystem");

        Email email= new Email();
        email.setDefaultAttributes();

        GeneratorContext context = new GeneratorContext(asList(email),5);

        return new MDVAction(identifier, eCommand, context);
    }
}