package mamei.de.mdv.system.data.entities;

import java.util.List;

import static java.util.Arrays.asList;

public class Entities {

    public static class Primary {

        public static final String IDENTIFIER = "PRIMARY";
        public static List<String> ATTRIBUTE_NAMES = asList(Person.IDENTIFIER);

        public static class Person {
            public static final String IDENTIFIER = "PERSON";
            public static final String NAME = "name";
            public static final String FIRST_NAMES = "firstName";
            public static final String LAST_NAMES = "lastName";
            public static final String DATE_OF_BIRTH = "dateOfBirth";
            public static final String AGE = "age";
            public static final String PLACE_OF_BIRTH = "placeOfBirth";
            public static final String NATIONALITY = "nationality";
            public static final String GENDER = "gender";
            public static final String PROFESSION = "profession";
            public static final String RESIDENCE = "residence";
            public static final String HEIGHT = "height";
            public static final String WEIGHT = "weight";
            public static final String EYE_COLOR = "eyeColor";
            public static final String HAIR_COLOR = "hairColor";
            public static final String SKIN_COLOR = "skinColor";
            public static final String BODY_TYPE = "bodyType";
            public static final String DISTINCTIVE_MARKS = "distinctiveMarks";
            public static final String PERSONALITY_TRAITS = "personalityTraits";
            public static final String STRENGTHS = "strengths";
            public static final String WEAKNESSES = "weaknesses";
            public static final String HOBBIES = "hobbies";
            public static final String CONTACT_INFO = "contactInfo";
            public static final String MARITAL_STATUS = "maritalStatus";
            public static List<String> ATTRIBUTE_NAMES = asList(IDENTIFIER, NAME, FIRST_NAMES, LAST_NAMES, DATE_OF_BIRTH,
                    AGE, PLACE_OF_BIRTH, NATIONALITY, GENDER, PROFESSION, RESIDENCE, HEIGHT, WEIGHT, EYE_COLOR,
                    HAIR_COLOR, SKIN_COLOR, BODY_TYPE, DISTINCTIVE_MARKS, PERSONALITY_TRAITS, STRENGTHS, WEAKNESSES,
                    HOBBIES, CONTACT_INFO, MARITAL_STATUS);
        }
    }

    public static class Secondary {

        public static final String IDENTIFIER = "SECONDARY";
        public static List<String> ATTRIBUTE_NAMES = asList(Communication.IDENTIFIER);

        public static class Communication {

            public static final String IDENTIFIER = "communication";
            public static List<String> ATTRIBUTE_NAMES = asList(Email.IDENTIFIER);

            public static class Email {

                public static final String IDENTIFIER = "EMAIL";
                public static final String PROVIDER = "provider";
                public static final String NAME = "name";
                public static List<String> ATTRIBUTE_NAMES = asList(IDENTIFIER, PROVIDER, NAME);
            }
        }
    }

}

