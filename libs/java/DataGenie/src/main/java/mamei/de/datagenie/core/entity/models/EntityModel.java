package mamei.de.datagenie.core.entity.models;

import java.util.List;

import static java.util.Arrays.asList;

public final class EntityModel {

    private EntityModel() {
        throw new UnsupportedOperationException("EntityDefinitions is a utility class and cannot be instantiated.");
    }

    public final static class Person {
        public static final String IDENTIFIER = "PERSON";
        public static final String NAME = "NAME";
        public static final String FIRST_NAMES = "FIRST_NAMES";
        public static final String LAST_NAMES = "LAST_NAMES";
        public static final String DATE_OF_BIRTH = "DATE_OF_BIRTH";
        public static final String AGE = "AGE";
        public static final String PLACE_OF_BIRTH = "PLACE_OF_BIRTH";
        public static final String NATIONALITY = "NATIONALITY";
        public static final String GENDER = "GENDER";
        public static final String PROFESSION = "PROFESSION";
        public static final String RESIDENCE = "RESIDENCE";
        public static final String HEIGHT = "HEIGHT";
        public static final String WEIGHT = "WEIGHT";
        public static final String EYE_COLOR = "EYE_COLOR";
        public static final String HAIR_COLOR = "HAIR_COLOR";
        public static final String SKIN_COLOR = "SKIN_COLOR";
        public static final String BODY_TYPE = "BODY_TYPE";
        public static final String DISTINCTIVE_MARKS = "DISTINCTIVE_MARKS";
        public static final String PERSONALITY_TRAITS = "PERSONALITY_TRAITS";
        public static final String STRENGTHS = "STRENGTHS";
        public static final String WEAKNESSES = "WEAKNESSES";
        public static final String HOBBIES = "HOBBIES";
        public static final String CONTACT_INFO = "CONTACT_INFO";
        public static final String MARITAL_STATUS = "MARITAL_STATUS";
        public static List<String> ATTRIBUTE_NAMES = asList(IDENTIFIER, NAME, FIRST_NAMES, LAST_NAMES, DATE_OF_BIRTH,
                AGE, PLACE_OF_BIRTH, NATIONALITY, GENDER, PROFESSION, RESIDENCE, HEIGHT, WEIGHT, EYE_COLOR,
                HAIR_COLOR, SKIN_COLOR, BODY_TYPE, DISTINCTIVE_MARKS, PERSONALITY_TRAITS, STRENGTHS, WEAKNESSES,
                HOBBIES, CONTACT_INFO, MARITAL_STATUS);
    }


    public final static class Communication {

        public static final String IDENTIFIER = "COMMUNICATION";
        public static List<String> ATTRIBUTE_NAMES = asList(Email.IDENTIFIER, Mobile.IDENTIFIER);

        public final static class Email {

            public static final String IDENTIFIER = "EMAIL";
            public static final String PROVIDER = "PROVIDER";
            public static final String NAME = "NAME";
            public static List<String> ATTRIBUTE_NAMES = asList(PROVIDER, NAME);
        }

        public final static class Mobile {
            public static final String IDENTIFIER = "MOBILE";
            public static final String PROVIDER = "PROVIDER";
            public static final String NUMBER = "NUMBER";
            public static List<String> ATTRIBUTE_NAMES = asList(PROVIDER, NUMBER);
        }

    }


}
