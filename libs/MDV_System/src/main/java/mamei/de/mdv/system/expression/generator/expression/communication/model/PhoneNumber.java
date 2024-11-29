package mamei.de.mdv.system.expression.generator.expression.communication.model;

import mamei.de.mdv.system.expression.generator.expression.communication.ICommunication;

public class PhoneNumber implements ICommunication {

    private final String phoneNumber;

    public PhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String getValue() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "Phone number: " + phoneNumber;
    }
}
