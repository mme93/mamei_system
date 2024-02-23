package com.apigateway.api.eureka.service.table;

import com.apigateway.api.eureka.assets.EurekaDiscoveryClientNameTable;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

/**
 * Tests for the Eureka Discovery Client Name Table.
 */
public class DiscoverClientTableTest {

    /**
     * Tests if the service registration name is as expected.
     */
    @Test
    public void testServicesRegistrationName(){
        Assertions.assertTrue(EurekaDiscoveryClientNameTable.ServiceRegistration.equals("SERVICE_REGISTRATION_NOT_IN_LIST"));
    }

    /**
     * Tests if the system service names are as expected.
     */
    @Test
    public void testSystemServicesNames(){
        Assertions.assertTrue(EurekaDiscoveryClientNameTable.ApiGateWay.equals("APIGATEWAY"));
        Assertions.assertTrue(EurekaDiscoveryClientNameTable.SystemManagerAPI.equals("SYSTEM"));
        Assertions.assertTrue(EurekaDiscoveryClientNameTable.ConfigManagerAPI.equals("CONFIG_NOT_IN_LIST"));
        Assertions.assertTrue(EurekaDiscoveryClientNameTable.HealthManagerAPI.equals("HEALTHMANAGER"));
        Assertions.assertTrue(EurekaDiscoveryClientNameTable.SecurityGatewayAPI.equals("SECURITYGATEWAY"));
        Assertions.assertTrue(EurekaDiscoveryClientNameTable.DataStorageAPI.equals("DATASTORAGE"));
        Assertions.assertTrue(EurekaDiscoveryClientNameTable.MassDataPoolAPI.equals("MASSDATAPOOL"));
    }

    /**
     * Tests if the project service names are as expected.
     */
    @Test
    public void testProjectServices(){
        Assertions.assertTrue(EurekaDiscoveryClientNameTable.UserAPI.equals("USER"));
        Assertions.assertTrue(EurekaDiscoveryClientNameTable.SudokuAPI.equals("SUDOKU"));
        Assertions.assertTrue(EurekaDiscoveryClientNameTable.DashboardAPI.equals("DASHBOARD"));
        Assertions.assertTrue(EurekaDiscoveryClientNameTable.ShoppinglistAPI.equals("SHOPPINGLIST"));
        Assertions.assertTrue(EurekaDiscoveryClientNameTable.GamesManager.equals("GAMESMANAGER"));
        Assertions.assertTrue(EurekaDiscoveryClientNameTable.Mameie_FSM.equals("MAMEI_FSM"));
    }

    /**
     * Tests if the size of the service client name list is as expected.
     */
    @Test
    public void testServiceClientNameSize(){
        Assertions.assertEquals(EurekaDiscoveryClientNameTable.eurekaDiscoverClientNameList.size(), 12);
    }

    /**
     * Tests if all client names in the EurekaDiscoveryClientNameTable are present in the list of expected names.
     */
    @Test
    public void testEurekaDiscoveryClientNameAsStringIsComplete(){
        for(String clientName : EurekaDiscoveryClientNameTable.eurekaDiscoverClientNameList){
            Assertions.assertTrue(EurekaDiscoveryClientNameTable.eurekaDiscoverClientNames.contains(clientName));
        }
    }
}
