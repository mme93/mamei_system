package com.apigateway.api.eureka.service;

import com.apigateway.api.eureka.assets.EurekaDiscoveryClientNameTable;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

/**
 * Service for interacting with Eureka Discovery Client.
 */
@Service
@RequiredArgsConstructor
public class DiscoveryClientService {

    private final DiscoveryClient discoveryClient;

    /**
     * Retrieves the address of the Sudoku client.
     */
    public String getSudokuClientAdress() {
        return discoveryClient.getInstances(EurekaDiscoveryClientNameTable.SudokuAPI).get(0).getUri().toString();
    }

    /**
     * Retrieves the address of the System client.
     */
    public String getSystemClientAdress() {
        return discoveryClient.getInstances(EurekaDiscoveryClientNameTable.SystemManagerAPI).get(0).getUri().toString();
    }

    /**
     * Retrieves the address of the Gateway client.
     */
    public String getSecurityGatewayClientAdress() {
        return discoveryClient.getInstances(EurekaDiscoveryClientNameTable.SecurityGatewayAPI).get(0).getUri().toString();
    }

    /**
     * Retrieves the address of the User client.
     */
    public String getUserClientAdress() {
        return discoveryClient.getInstances(EurekaDiscoveryClientNameTable.UserAPI).get(0).getUri().toString();
    }

    /**
     * Retrieves the address of the Dashboard client.
     */
    public String getDashboardClientAdress() {
        return discoveryClient.getInstances(EurekaDiscoveryClientNameTable.DashboardAPI).get(0).getUri().toString();
    }

    /**
     * Retrieves the address of the MassDataPool client.
     */
    public String getMassDataPoolClientAdress() {
        return discoveryClient.getInstances(EurekaDiscoveryClientNameTable.MassDataPoolAPI).get(0).getUri().toString();
    }

    /**
     * Retrieves the address of the Games Manager client.
     */
    public String getGamesManagerClientAdress() {
        return discoveryClient.getInstances(EurekaDiscoveryClientNameTable.GamesManager).get(0).getUri().toString();
    }

    /**
     * Retrieves the address of the Mameie FSM client.
     */
    public String getMameieFsmClientAdress() {
        return discoveryClient.getInstances(EurekaDiscoveryClientNameTable.Mameie_FSM).get(0).getUri().toString();
    }

    /**
     * Retrieves the address of the Datastorage Manager client.
     */
    public String getDatastorageManagerClientAdress() {
        return discoveryClient.getInstances(EurekaDiscoveryClientNameTable.DataStorageAPI).get(0).getUri().toString();
    }

    /**
     * Retrieves the address of the Health Manager client.
     */
    public String getHealtManagerAdressByName() {
        return discoveryClient.getInstances(EurekaDiscoveryClientNameTable.HealthManagerAPI).get(0).getUri().toString();
    }

    /**
     * Retrieves the address of the Shopping List client.
     */
    public String getShoppingListAdressByName() {
        return discoveryClient.getInstances(EurekaDiscoveryClientNameTable.ShoppinglistAPI).get(0).getUri().toString();
    }

    /**
     * Retrieves the address of the Config Manager client.
     */
    public String getConfigManagerByName() {
        return discoveryClient.getInstances(EurekaDiscoveryClientNameTable.ConfigManagerAPI).get(0).getUri().toString();
    }

    /**
     * Retrieves the address of a client by name.
     *
     * @param clientName The name of the client.
     * @return The address of the client.
     */
    public String getClientAdressByName(String clientName) {
        return discoveryClient.getInstances(clientName).get(0).getUri().toString();
    }

    /**
     * Checks if a Eureka discovery client exists by name.
     *
     * @param name The name of the client to check.
     * @return true if the client exists, false otherwise.
     */
    public boolean existEurekaDiscoveryClientByName(String name) {
        return discoveryClient.getServices().stream().filter(microserviceName -> microserviceName.equals(name)).count() == 1;
    }

}
