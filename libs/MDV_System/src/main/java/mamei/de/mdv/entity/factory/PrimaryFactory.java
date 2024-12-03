package mamei.de.mdv.entity.factory;

import mamei.de.mdv.entity.Entity;
import mamei.de.mdv.entity.attribute.Attribute;
import mamei.de.mdv.entity.primary.person.Person;

import java.util.List;

public class PrimaryFactory {

    public static Entity generatePerson() {
        return new Person();
    }

    public static Entity generatePerson(List<Attribute> attributes) {
        Person person = new Person();
        person.clearAttributes();
        attributes.forEach(attribute -> person.addAttribute(attribute));
        return person;
    }
}
