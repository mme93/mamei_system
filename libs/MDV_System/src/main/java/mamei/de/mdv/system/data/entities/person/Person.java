package mamei.de.mdv.system.data.entities.person;

import mamei.de.mdv.system.data.entities.Entities;
import mamei.de.mdv.system.data.entities.Entity;

public class Person extends Entity {

    public static final String IDENTIFIER = Entities.Person.IDENTIFIER;

    public Person() {
        super(IDENTIFIER);
    }
}
