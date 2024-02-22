package com.apigateway.api.eureka.service;

import com.apigateway.api.eureka.assets.EurekaDiscoveryClientNameTable;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.net.URI;

import static java.util.Arrays.asList;

/**
 * Unit tests for the DiscoveryClientService class.
 */
public class DiscoveryClientServiceTest {

    /**
     * Mocked DiscoveryClient instance.
     */
    @Mock
    private DiscoveryClient discoveryClient;

    /**
     * Instance of DiscoveryClientService to be tested.
     */
    private DiscoveryClientService discoveryClientService;

    /**
     * Sets up the test environment by initializing Mockito mocks and creating an instance of DiscoveryClientService.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        discoveryClientService = new DiscoveryClientService(discoveryClient);
    }

    /**
     * Test case for getting the system manager address.
     */
    @Test
    public void testGetSystemManagerAddress() {
        String systemManagerAPI = EurekaDiscoveryClientNameTable.SystemManagerAPI;
        mockDiscoveryClient(systemManagerAPI);
        String resultAddress = discoveryClientService.getSystemClientAdress();

        Assertions.assertEquals(systemManagerAPI, resultAddress);
    }

    /**
     * Test case for getting the health manager address.
     */
    @Test
    public void testGetHealthManagerAddress() {
        String healthManagerAPI = EurekaDiscoveryClientNameTable.HealthManagerAPI;
        mockDiscoveryClient(healthManagerAPI);
        String resultAddress = discoveryClientService.getHealtManagerAdressByName();

        Assertions.assertEquals(healthManagerAPI, resultAddress);
    }

    /**
     * Test case for getting the security gateway address.
     */
    @Test
    public void testGetSecurityGatewayAddress() {
        String securityGatewayAPI = EurekaDiscoveryClientNameTable.SecurityGatewayAPI;
        mockDiscoveryClient(securityGatewayAPI);
        String resultAddress = discoveryClientService.getSecurityGatewayClientAdress();

        Assertions.assertEquals(securityGatewayAPI, resultAddress);
    }

    /**
     * Test case for getting the data storage address.
     */
    @Test
    public void testGetDataStorageAddress() {
        String dataStorageAPI = EurekaDiscoveryClientNameTable.DataStorageAPI;
        mockDiscoveryClient(dataStorageAPI);
        String resultAddress = discoveryClientService.getDatastorageManagerClientAdress();

        Assertions.assertEquals(dataStorageAPI, resultAddress);
    }

    /**
     * Test case for getting the mass data pool address.
     */
    @Test
    public void testGetMassDataPooAddress() {
        String massDataPoolAPI = EurekaDiscoveryClientNameTable.MassDataPoolAPI;
        mockDiscoveryClient(massDataPoolAPI);
        String resultAddress = discoveryClientService.getMassDataPoolClientAdress();

        Assertions.assertEquals(massDataPoolAPI, resultAddress);
    }

    /**
     * Test case for getting the dashboard address.
     */
    @Test
    public void testGetDashboardAddress() {
        String apiGateWay = EurekaDiscoveryClientNameTable.DashboardAPI;
        mockDiscoveryClient(apiGateWay);
        String resultAddress = discoveryClientService.getDashboardClientAdress();

        Assertions.assertEquals(apiGateWay, resultAddress);
    }

    /**
     * Test case for getting the user address.
     */
    @Test
    public void testGetUserAddress() {
        String userAPI = EurekaDiscoveryClientNameTable.UserAPI;
        mockDiscoveryClient(userAPI);
        String resultAddress = discoveryClientService.getUserClientAdress();

        Assertions.assertEquals(userAPI, resultAddress);
    }

    /**
     * Test case for getting the sudoku address.
     */
    @Test
    public void testGetSudokuAddress() {
        String sudokuAPI = EurekaDiscoveryClientNameTable.SudokuAPI;
        mockDiscoveryClient(sudokuAPI);
        String resultAddress = discoveryClientService.getSudokuClientAdress();

        Assertions.assertEquals(sudokuAPI, resultAddress);
    }

    /**
     * Test case for getting the shopping list address.
     */
    @Test
    public void testGetShoppingListAddress() {
        String shoppinglistAPI = EurekaDiscoveryClientNameTable.ShoppinglistAPI;
        mockDiscoveryClient(shoppinglistAPI);
        String resultAddress = discoveryClientService.getShoppingListAdressByName();

        Assertions.assertEquals(shoppinglistAPI, resultAddress);
    }

    /**
     * Test case for getting the games manager address.
     */
    @Test
    public void testGetGamesManagerAddress() {
        String gamesManager = EurekaDiscoveryClientNameTable.GamesManager;
        mockDiscoveryClient(gamesManager);
        String resultAddress = discoveryClientService.getGamesManagerClientAdress();

        Assertions.assertEquals(gamesManager, resultAddress);
    }

    /**
     * Test case for getting the mameie fsm address.
     */
    @Test
    public void testGetMameieFSMAddress() {
        String mameie_fsm = EurekaDiscoveryClientNameTable.Mameie_FSM;
        mockDiscoveryClient(mameie_fsm);
        String resultAddress = discoveryClientService.getMameieFsmClientAdress();

        Assertions.assertEquals(mameie_fsm, resultAddress);
    }


    /**
     * Mocks the behavior of the DiscoveryClient for a given service name.
     *
     * @param serviceName The name of the service to mock.
     */
    private void mockDiscoveryClient(String serviceName) {
        URI apiGateWayUri = URI.create(serviceName);
        ServiceInstance serviceInstanceMock = Mockito.mock(ServiceInstance.class);
        Mockito.when(discoveryClient.getInstances(serviceName)).thenReturn(asList(serviceInstanceMock));
        Mockito.when(serviceInstanceMock.getUri()).thenReturn(apiGateWayUri);
    }


}
