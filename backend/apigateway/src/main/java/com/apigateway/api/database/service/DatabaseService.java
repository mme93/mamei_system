package com.apigateway.api.database.service;

import com.apigateway.api.discoveryclient.assets.EurekaDiscoveryClientNameTable;
import com.apigateway.api.discoveryclient.assets.table.UserRouterTable;
import com.apigateway.api.discoveryclient.service.DiscoveryClientService;
import com.apigateway.security.SecurityUserEntityRepository;
import com.apigateway.security.model.entity.SecurityUserEntity;
import com.apigateway.security.model.entity.UserCollection;
import com.apigateway.status.microservice.model.entity.MicroServiceEntity;
import com.apigateway.status.microservice.repository.MicroServiceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

@Service
@RequiredArgsConstructor
public class DatabaseService {

    private final DiscoveryClientService clientService;
    private final WebClient.Builder webClient;
    private final SecurityUserEntityRepository securityUserEntityRepository;
    private final MicroServiceRepository microServiceRepository;

    @Transactional
    public void rebuildDatabase() {
        System.err.println("Rebuild Database");
        callRebuildDatabase("http://services-meier.de:8998" + UserRouterTable.DATABASE_MANAGER_REBUILD_DATABASE);
        //callRebuildDatabase("http://localhost:8998"+UserRouterTable.DATABASE_MANAGER_REBUILD_DATABASE);

    }

    @Transactional
    public void initDatabase() {
        System.err.println("Init Database");
        cleanSecurityUser(asList("admin", "superAdmin", "user", "guest", "root"));
        initSecurityUser();
        for (String uri : createList(asList(UserRouterTable.USER_ID))) {
            //callInit(uri);
        }
        initMicroServices();
    }

    @Transactional
    private void cleanSecurityUser(List<String> userNamelist) {
        for (String userName : userNamelist) {
            securityUserEntityRepository.deleteByUsername(userName);
        }
    }

    private List<String> createList(List<String> idList) {
        List<String> uriList = new ArrayList<>();
        for (String id : idList) {
            if (id.equals(UserRouterTable.USER_ID)) {
                uriList.add(clientService.getUserClientAdress() + UserRouterTable.URI_USER_DATABASE_INIT);
            }
        }
        return uriList;
    }

    private void callRebuildDatabase(String uri) {
        webClient
                .build()
                .post()
                .uri(uri)
                .retrieve()
                .bodyToMono(Void.class).block();
    }

    private Object callInit(String uri) {
        return webClient
                .build()
                .post()
                .uri(uri)
                .retrieve()
                .bodyToMono(Object.class).block();
    }

    public void initMicroServices() {
        if (!microServiceRepository.existsByEurekaServiceName(EurekaDiscoveryClientNameTable.ServiceRegistration)) {
            microServiceRepository.save(new MicroServiceEntity(
                    EurekaDiscoveryClientNameTable.ServiceRegistration
                    , "work"
                    , "Eureka Service Registration"
                    , "Registers all Spring Boot Microservices in Eureka by name and lists them."
            ));

        }
        if (!microServiceRepository.existsByEurekaServiceName(EurekaDiscoveryClientNameTable.ApiGateWay)) {
            microServiceRepository.save(new MicroServiceEntity(
                    EurekaDiscoveryClientNameTable.ApiGateWay
                    , "share"
                    , "ApiGateway"
                    , "Serves as the first point of contact for all requests and distributes them to the target services after you have passed the security check from the security gateway.\n" +
                    "\n" +
                    "Takes care of all areas of network security such as token procedures."
            ));
        }
        if (!microServiceRepository.existsByEurekaServiceName(EurekaDiscoveryClientNameTable.SystemManagerAPI)) {
            microServiceRepository.save(new MicroServiceEntity(
                    EurekaDiscoveryClientNameTable.SystemManagerAPI
                    , "dns"
                    , "System Manager"
                    , "1) Central logging and monitoring:\n" +
                    "2) Caching strategies:\n" +
                    "3) Central event bus handling."
            ));
        }
        if (!microServiceRepository.existsByEurekaServiceName(EurekaDiscoveryClientNameTable.ConfigManagerAPI)) {
            microServiceRepository.save(new MicroServiceEntity(
                    EurekaDiscoveryClientNameTable.ConfigManagerAPI
                    , "settings"
                    , "Config Manager"
                    , "Provides the configurations for the other micro-services and cannot be found in the Eureka list."
            ));
        }
        if (!microServiceRepository.existsByEurekaServiceName(EurekaDiscoveryClientNameTable.HealthManagerAPI)) {
            microServiceRepository.save(new MicroServiceEntity(
                    EurekaDiscoveryClientNameTable.HealthManagerAPI
                    , "monitor_heart"
                    , "Health Manager"
                    , "Should monitor all micro-services and analyze the status of the services with the help of the actuator."
            ));
        }
        if (!microServiceRepository.existsByEurekaServiceName(EurekaDiscoveryClientNameTable.SecurityGatewayAPI)) {
            microServiceRepository.save(new MicroServiceEntity(
                    EurekaDiscoveryClientNameTable.SecurityGatewayAPI
                    , "security"
                    , "Security Service"
                    , "Overview about current and past Security-Protocols or Events."
            ));
        } else if (!microServiceRepository.existsByEurekaServiceName(EurekaDiscoveryClientNameTable.UserAPI)) {
            microServiceRepository.save(new MicroServiceEntity(
                    EurekaDiscoveryClientNameTable.UserAPI
                    , "person"
                    , "User Service"
                    , "The normal user for logging into the system runs via the security_user, which is managed on the API gateway. The"
                    + "remaining Users are in the User Services and are an extension of the security_user."
            ));
        }
        if (!microServiceRepository.existsByEurekaServiceName(EurekaDiscoveryClientNameTable.SudokuAPI)) {
            microServiceRepository.save(new MicroServiceEntity(
                    EurekaDiscoveryClientNameTable.SudokuAPI
                    , "apps"
                    , "Sudoku Service"
                    , "You can play different Sudoku Level."
            ));
        }
        if (!microServiceRepository.existsByEurekaServiceName(EurekaDiscoveryClientNameTable.DashboardAPI)) {
            microServiceRepository.save(new MicroServiceEntity(
                    EurekaDiscoveryClientNameTable.DashboardAPI
                    , "insights"
                    , "Dashboard Service"
                    , "You can create different entities for specific utils and display a lot of diagrams from data-pool."
            ));
        }
        if (!microServiceRepository.existsByEurekaServiceName(EurekaDiscoveryClientNameTable.ShoppinglistAPI)) {
            microServiceRepository.save(new MicroServiceEntity(
                    EurekaDiscoveryClientNameTable.ShoppinglistAPI
                    , "warehouse"
                    , "Shopping List Service"
                    , "You can create shopping list."
            ));
        }
        if (!microServiceRepository.existsByEurekaServiceName(EurekaDiscoveryClientNameTable.GamesManager)) {
            microServiceRepository.save(new MicroServiceEntity(
                    EurekaDiscoveryClientNameTable.GamesManager
                    , "toys"
                    , "Games Manager"
                    , "Administration all about the different games like Sudoku."
            ));
        }
        if (!microServiceRepository.existsByEurekaServiceName(EurekaDiscoveryClientNameTable.Mameie_FSM)) {
            microServiceRepository.save(new MicroServiceEntity(
                    EurekaDiscoveryClientNameTable.Mameie_FSM
                    , "tune"
                    , "Mameie FSM (Frontend System Manager)"
                    , "Administration all about the settings and more."
            ));
        }
    }

    public void initSecurityUser() {
        PasswordEncoder pw = new BCryptPasswordEncoder();

        if (!securityUserEntityRepository.existsByUsername("admin")) {
            securityUserEntityRepository.save(new SecurityUserEntity(
                    "admin",
                    pw.encode("test123"),
                    UserCollection.MODULE_ADMIN
            ));
        }
        if (!securityUserEntityRepository.existsByUsername("superAdmin")) {
            securityUserEntityRepository.save(new SecurityUserEntity(
                    "superAdmin",
                    pw.encode("test123"),
                    UserCollection.SUPER_ADMIN
            ));
        }
        if (!securityUserEntityRepository.existsByUsername("user")) {
            securityUserEntityRepository.save(new SecurityUserEntity(
                    "user",
                    pw.encode("test123"),
                    UserCollection.USER
            ));
        }
        if (!securityUserEntityRepository.existsByUsername("guest")) {
            securityUserEntityRepository.save(new SecurityUserEntity(
                    "guest",
                    pw.encode("test123"),
                    UserCollection.GUEST
            ));
        }
        if (!securityUserEntityRepository.existsByUsername("root")) {
            securityUserEntityRepository.save(new SecurityUserEntity(
                    "root",
                    pw.encode("test123"),
                    UserCollection.SYSTEM_ADMIN
            ));
        }
    }
}
