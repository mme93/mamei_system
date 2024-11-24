package mamei.de.mdv.system;

import java.util.List;

import static java.util.Arrays.asList;

public abstract class System implements ISystem {

    public static final String DEFAULT_SYSTEM = "DEFAULT_SYSTEM";
    public static final String DATA_GENERATOR_SYSTEM = "DATA_GENERATOR_SYSTEM";
    public static final List<String>SYSTEM_NAMES=asList(DATA_GENERATOR_SYSTEM);
    private final String SYSTEM_NAME;


    public System(String SYSTEM_NAME) {
        this.SYSTEM_NAME = SYSTEM_NAME;
    }

    public String getSystemName() {
        return SYSTEM_NAME;
    }
}
