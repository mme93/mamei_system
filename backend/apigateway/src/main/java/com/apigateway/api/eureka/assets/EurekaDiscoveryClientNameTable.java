package com.apigateway.api.eureka.assets;

import java.util.List;

import static java.util.Arrays.asList;

public class EurekaDiscoveryClientNameTable {

    //Services Registry
    public static final String ServiceRegistration = "SERVICE_REGISTRATION_NOT_IN_LIST";

    //System Services Range 9000-9049
    public static final String ApiGateWay = "APIGATEWAY";
    public static final String SystemManagerAPI = "SYSTEM";
    public static final String ConfigManagerAPI = "CONFIG_NOT_IN_LIST";
    public static final String HealthManagerAPI = "HEALTHMANAGER";
    public static final String SecurityGatewayAPI = "SECURITYGATEWAY";
    public static final String DataStorageAPI = "DATASTORAGE";
    public static final String MassDataPoolAPI = "MASSDATAPOOL";

    //Project Services Range 9050-9099
    public static final String UserAPI = "USER";
    public static final String SudokuAPI = "SUDOKU";
    public static final String DashboardAPI = "DASHBOARD";
    public static final String ShoppinglistAPI = "SHOPPINGLIST";
    public static final String GamesManager = "GAMESMANAGER";
    public static final String Mameie_FSM = "MAMEI_FSM";

    public static final List<String> eurekaDiscoverClientNameList = asList(ApiGateWay, DashboardAPI, DataStorageAPI,
            GamesManager, HealthManagerAPI, Mameie_FSM, SecurityGatewayAPI, ShoppinglistAPI, SudokuAPI, SystemManagerAPI,
            UserAPI, MassDataPoolAPI);

    public static final String eurekaDiscoverClientNames = "APIGATEWAY, DASHBOARD, DATASTORAGE, GAMESMANAGER, HEALTHMANAGER, " +
            "MAMEI_FSM, SECURITYGATEWAY, SHOPPINGLIST, SUDOKU, SYSTEM, USER, MASSDATAPOOL";
}
