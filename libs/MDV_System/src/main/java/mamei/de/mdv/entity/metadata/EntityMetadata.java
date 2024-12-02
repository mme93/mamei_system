package mamei.de.mdv.entity.metadata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EntityMetadata {

    private final String identifier;
    private final List<String> standardAttributes;

    public EntityMetadata(String identifier, List<String> standardAttributes) {
        this.identifier = identifier;
        this.standardAttributes = new ArrayList<>(standardAttributes);
    }

    public String getIdentifier() {
        return identifier;
    }

    public List<String> getStandardAttributes() {
        return Collections.unmodifiableList(standardAttributes);
    }

    public static EntityMetadata forPerson() {
        return new EntityMetadata(
                "PERSON",
                List.of("firstName", "lastName", "age", "gender", "nationality", "email", "mobileNumber")
        );
    }

    public static EntityMetadata forEmail() {
        return new EntityMetadata(
                "EMAIL",
                List.of("address", "provider")
        );
    }
}
