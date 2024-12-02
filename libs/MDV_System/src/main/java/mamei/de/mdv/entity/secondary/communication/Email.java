package mamei.de.mdv.entity.secondary.communication;

import mamei.de.mdv.entity.Entities;
import mamei.de.mdv.entity.secondary.Secondary;

public class Email extends Secondary {

    public Email() {
        super("EMAIL");
    }

    public void setEmailDetails(String address, String provider) {
        setAttribute("address", address);
        setAttribute("provider", provider);
    }

    public String getAddress() {
        return (String) getAttribute("address");
    }

    public String getProvider() {
        return (String) getAttribute("provider");
    }

    @Override
    public void setDefaultAttributes() {
        for(String attribute: Entities.Secondary.Communication.Email.ATTRIBUTE_NAMES){
            setAttribute(attribute);
        }
    }
}
