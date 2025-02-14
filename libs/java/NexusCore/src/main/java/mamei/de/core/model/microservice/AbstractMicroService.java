package mamei.de.core.model.microservice;

import mamei.de.core.model.microservice.enums.EMicroService;

public abstract class AbstractMicroService implements IMicroService{

    private EMicroService microService;
    private int port;
    private String description;
    private String version;
    private String applicationName;

    public AbstractMicroService(EMicroService microService, int port, String description, String version, String applicationName) {
        this.microService = microService;
        this.port = port;
        this.description = description;
        this.version = version;
        this.applicationName = applicationName;
    }
}
