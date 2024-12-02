package mamei.de.mdv.system.expression.generator.expression.communication;

import mamei.de.mdv.system.expression.generator.expression.communication.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class CommunicationFactory {

    private static final Random RANDOM = new Random();


    public static List<ICommunication> createCommunication(ECommunicationType type, int amount) {
        switch (type) {
            case MOBILE_PHONE:
                return generateMobileNumber(amount);
            case EMAIL:
                return generateEmail(amount);
            default:
                throw new IllegalArgumentException("Invalid communication type");
        }
    }


    private static List<ICommunication> generateMobileNumber(int amount) {
        List<ICommunication> communications = new ArrayList<>();
        for (int j = 0; j < amount; j++) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 10; i++) {
                sb.append(RANDOM.nextInt(10));
            }
            Map<String, String> mobileProviders = CommunicationSetup.getMobileProviders();
            int randomProviderIndex = RANDOM.nextInt(mobileProviders.size());
            String providerNumber = mobileProviders.get(mobileProviders.get(randomProviderIndex));
            communications.add(new MobileNumber(String.format("+% %s", providerNumber, sb)));
        }

        return communications;
    }

    private static List<ICommunication> generateEmail(int amount) {
        List<ICommunication> communications = new ArrayList<>();

        return communications;
     /*
        return new Email(String.format("%s@%s",
                getRandomElement(null),
                getRandomElement(CommunicationSetup.EMAIL_PROVIDER)));

      */
    }

    private static String getRandomElement(List<String> elements) {
        return elements.get(new Random().nextInt(elements.size()));
    }

}