package mamei.de.mdv.system.expression.generator.expression.communication;

import mamei.de.mdv.system.data.entities.Entities;
import mamei.de.mdv.system.expression.generator.expression.communication.model.Email;
import mamei.de.mdv.system.expression.generator.expression.communication.model.MobileNumber;
import mamei.de.mdv.system.expression.generator.expression.communication.model.PhoneNumber;

public class CommunicationFactory {

    public enum CommunicationType {
        PHONE_NUMBER,
        MOBILE_PHONE,
        EMAIL
    }

    public static ICommunication createCommunication(CommunicationType type, String value) {
        switch (type) {
            case PHONE_NUMBER:
                return new PhoneNumber(value);
            case MOBILE_PHONE:
                return new MobileNumber(value);
            case EMAIL:
                return new Email(value);
            default:
                throw new IllegalArgumentException("Invalid communication type");
        }
    }

    private static String generatePhoneNumber() {
        return null;
    }

    private static String generateMobileNumber() {
        return null;
    }

    private static String generateEmail() {
        return null;
    }


}
