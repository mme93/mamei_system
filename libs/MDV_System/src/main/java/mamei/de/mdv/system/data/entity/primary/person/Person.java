package mamei.de.mdv.system.data.entity.primary.person;

import mamei.de.mdv.system.data.entity.primary.Primary;

import java.util.List;

public class Person extends Primary {

    public Person() {
        super("PERSON");
        setDefaultAttributes();
    }

    @Override
    public void setDefaultAttributes() {
        setAttribute("firstName", null);
        setAttribute("lastName", null);
        setAttribute("age", null);
        setAttribute("gender", null);
    }

    public void setFirstName(String firstName) {
        setAttribute("firstName", firstName);
    }

    public String getFirstName() {
        return (String) getAttribute("firstName");
    }
}