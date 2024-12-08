package mamei.de.mdv.system.expression.generator.expression.communication.model;

import mamei.de.mdv.system.expression.generator.expression.communication.ICommunication;

public class MobileNumber implements ICommunication {

    private final String mobileNumber;

    public MobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Override
    public String getValue() {
        return mobileNumber;
    }

    @Override
    public String toString() {
        return "Mobile number: " + mobileNumber;
    }
}
