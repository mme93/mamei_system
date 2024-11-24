package mamei.de.mdv.system.data.entities.secondary.communication;

import mamei.de.mdv.system.data.entities.Entities;
import mamei.de.mdv.system.data.entities.secondary.ISecondary;

public class Email implements ISecondary {

    private final String provider;
    private final String name;

    public Email(String provider, String name) {
        this.provider = provider;
        this.name = name;
    }

    public String getProvider() {
        return provider;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getIdentifier() {
        return Entities.Secondary.Communication.Email.IDENTIFIER;
    }
}
