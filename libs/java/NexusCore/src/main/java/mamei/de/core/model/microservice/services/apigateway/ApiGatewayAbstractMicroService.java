package mamei.de.core.model.microservice.services.apigateway;


import mamei.de.core.model.microservice.AbstractMicroService;
import mamei.de.core.model.microservice.enums.EMicroService;

public class ApiGatewayAbstractMicroService extends AbstractMicroService {

    public ApiGatewayAbstractMicroService(EMicroService microService, int port, String description, String version, String applicationName) {
        super(EMicroService.API_GATEWAY, port, description, version, applicationName);
    }
}
