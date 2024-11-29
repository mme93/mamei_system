package mamei.de.mdv.system.expression.generator.expression.communication.model;

import mamei.de.mdv.system.expression.generator.expression.communication.ICommunication;

public class Email implements ICommunication {

    private final String emailAddress;

    public Email(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String getValue() {
        return emailAddress;
    }

    @Override
    public String toString() {
        return "Email: " + emailAddress;
    }
}
