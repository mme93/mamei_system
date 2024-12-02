package mamei.de;


import mamei.de.mdv.MDV;
import mamei.de.mdv.model.MDVAction;
import mamei.de.mdv.system.context.generator.GeneratorContext;
import mamei.de.mdv.system.data.entity.metadata.EntityMetadata;
import mamei.de.mdv.system.data.entity.primary.Primary;
import mamei.de.mdv.system.data.entity.primary.person.Person;
import mamei.de.mdv.system.expression.generator.expression.communication.model.Email;
import mamei.de.mdv.system.module.*;

public class Main {

    static MDV mdv = MDV.builder().withGenerator().build();

    public static void main(String[] args) {
        test2();
    }

    public static void test2() {
        Person person = new Person();
        person.setFirstName("Max");
        person.setAttribute("lastName", "Mustermann");
        person.setAttribute("age", 30);

        Email email = new Email();
        email.setEmailDetails("max.mustermann@example.com", "Gmail");

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
        GeneratorContext context = new GeneratorContext(10);

        return new MDVAction(identifier, eCommand, context);
    }
}