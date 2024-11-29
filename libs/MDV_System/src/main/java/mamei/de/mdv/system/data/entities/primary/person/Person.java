package mamei.de.mdv.system.data.entities.primary.person;

import mamei.de.mdv.system.data.entities.Entities;
import mamei.de.mdv.system.data.entities.primary.APrimary;
import mamei.de.mdv.system.data.entities.secondary.SecondaryOneToMany;

import java.util.List;

public class Person extends APrimary {

    private List<SecondaryOneToMany>communications;

    public Person() {
        super(Entities.Primary.Person.IDENTIFIER);
    }

    public void generateEmailAddress(){

    }

}
