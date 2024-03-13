package com.systemmanager.database.service.domains;

import com.systemmanager.eureka.assets.EurekaDiscoveryClientNameTable;
import com.systemmanager.microservice.status.model.entity.MicroServiceEntity;
import com.systemmanager.microservice.status.repository.MicroServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MicroServiceDomainDefaultDBService implements IDefaultDBService {

    private final MicroServiceRepository microServiceRepository;

    @Autowired
    public MicroServiceDomainDefaultDBService(MicroServiceRepository microServiceRepository) {
        this.microServiceRepository = microServiceRepository;
    }

    @Override
    public boolean loadDefaultDataIntoDatabase() {
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
        if (!microServiceRepository.existsByEurekaServiceName(EurekaDiscoveryClientNameTable.DataStorageAPI)) {
            microServiceRepository.save(new MicroServiceEntity(
                    EurekaDiscoveryClientNameTable.DataStorageAPI
                    , "save"
                    , "Data-Storage"
                    , "Administration all Data. You can update, delete, create and move files and folders."
            ));
        }
        return false;
    }

    @Override
    public boolean deleteAllData() {
        microServiceRepository.deleteAll();
        boolean isAllDataDeleted=microServiceRepository.findAll().size()==0;
        return isAllDataDeleted;
    }

    @Override
    public boolean deleteAllDefaultData() {
        return false;
    }

    @Override
    public boolean existTable() {
        return false;
    }

    @Override
    public boolean deleteTable() {
        return false;
    }

    @Override
    public boolean createTable() {
        return false;
    }
}
