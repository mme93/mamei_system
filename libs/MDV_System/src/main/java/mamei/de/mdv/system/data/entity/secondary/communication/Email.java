package mamei.de.mdv.system.data.entity.secondary.communication;

import mamei.de.mdv.system.data.entity.secondary.Secondary;

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
}
