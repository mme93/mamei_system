package mamei.de.mdv.entity.factory;

import mamei.de.mdv.entity.Entity;
import mamei.de.mdv.entity.secondary.communication.Email;

public class SecondaryFactory {

    public static Entity generateEmail(){
        return new Email();
    }

}
