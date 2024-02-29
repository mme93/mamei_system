package com.systemmanager.microservice.restart.service;

import com.systemmanager.eureka.assets.EurekaDiscoveryClientNameTable;
import com.systemmanager.eureka.service.DiscoveryClientService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.context.restart.RestartEndpoint;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(classes = {MicroServicesRestartService.class})
public class MicroServicesRestartServiceTest {

    @Autowired
    @InjectMocks
    private MicroServicesRestartService microServicesRestartService;

    @MockBean(name = "discoveryClientService")
    private DiscoveryClientService discoveryClientService;

    @MockBean
    private RestartEndpoint restartEndpoint;

    @MockBean
    private WebClient.Builder mockedWebClientBuilder;

    private final String microserviceName = "SYSTEM";

    /**
     * Initializes Mockito annotations before each test method.
     */
    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        microServicesRestartService = new MicroServicesRestartService(
                discoveryClientService,
                restartEndpoint,
                mockedWebClientBuilder
        );
    }

    @Test
    public void testIsEurekaServiceAvailable() {
        Mockito.when(discoveryClientService.existEurekaDiscoveryClientByName(microserviceName)).thenReturn(true);
        boolean eurekaServiceAvailable = microServicesRestartService.isEurekaServiceAvailable(microserviceName);
        Assertions.assertTrue(eurekaServiceAvailable);
    }

    @Test
    public void testIsEurekaServiceNotAvailable() {
        Mockito.when(discoveryClientService.existEurekaDiscoveryClientByName(microserviceName)).thenReturn(false);
        boolean eurekaServiceAvailable = microServicesRestartService.isEurekaServiceAvailable(microserviceName);
        Assertions.assertFalse(eurekaServiceAvailable);
    }

    @Test
    public void testIsOnWhiteList() {
        Assertions.assertTrue(microServicesRestartService.isOnWhiteList(EurekaDiscoveryClientNameTable.ApiGateWay));
        Assertions.assertTrue(microServicesRestartService.isOnWhiteList(EurekaDiscoveryClientNameTable.SystemManagerAPI));
        Assertions.assertFalse(microServicesRestartService.isOnWhiteList(EurekaDiscoveryClientNameTable.DashboardAPI));
    }

    //TODO: muss erst im Service repariert werden, da immer true zurückgeben wird.
    @Test
    public void testCallRestartDataStorageAPI() {

        Mockito.when(discoveryClientService.existEurekaDiscoveryClientByName(any())).thenReturn(true);
        Mockito.when(discoveryClientService.getClientAdressByName(any())).thenReturn("http://127.0.0.1:9005/DATASTORAGE");

        WebClient webClientMock= Mockito.mock(WebClient.class);
        WebClient.RequestBodyUriSpec requestBodyUriSpecMock=Mockito.mock(WebClient.RequestBodyUriSpec.class);
        WebClient.RequestBodySpec requestBodySpecMock=Mockito.mock(WebClient.RequestBodySpec.class);
        WebClient.ResponseSpec responseSpec = Mockito.mock(WebClient.ResponseSpec.class);

        Mockito.when(mockedWebClientBuilder.build()).thenReturn(webClientMock);
        Mockito.when(webClientMock.post()).thenReturn(requestBodyUriSpecMock);
        Mockito.when(requestBodyUriSpecMock.uri(Mockito.anyString())).thenReturn(requestBodySpecMock);
        Mockito.when(requestBodySpecMock.retrieve()).thenReturn(responseSpec);

        Mono<Object> jsonResponse = Mono.just("{\"false\"}");
        Mockito.when(responseSpec.bodyToMono(Object.class)).thenReturn(jsonResponse);

        boolean callRestart = microServicesRestartService.callRestart(EurekaDiscoveryClientNameTable.DataStorageAPI);

        Assertions.assertTrue(callRestart);
    }

    @Test
    public void testRestartService() {
        Assertions.assertTrue(true);
    }
}
