package mamei.de;

import mamei.de.datagenie.core.entity.models.EEntityTyp;
import mamei.de.datagenie.system.entity.builder.EntityBuilder;

public class Main {

    public static void main(String[] args) {

        EntityBuilder builder = EntityBuilder
                .builder()
                .withCommunication(EEntityTyp.HANDY)
                .build();
    }
}