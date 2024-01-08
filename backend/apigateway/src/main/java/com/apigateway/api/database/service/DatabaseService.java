package com.apigateway.api.database.service;

import com.apigateway.api.discoveryclient.assets.table.UserRouterTable;
import com.apigateway.api.discoveryclient.service.DiscoveryClientService;
import com.apigateway.security.SecurityUserEntityRepository;
import com.apigateway.security.model.entity.SecurityUserEntity;
import com.apigateway.security.model.entity.UserCollection;
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

    @Transactional
    public void rebuildDatabase() {
        System.err.println("Rebuild Database");
        //callRebuildDatabase("http://services-meier.de:8998"+UserRouterTable.DATABASE_MANAGER_REBUILD_DATABASE);
        callRebuildDatabase("http://localhost:8998"+UserRouterTable.DATABASE_MANAGER_REBUILD_DATABASE);
        cleanSecurityUser(asList("admin", "superAdmin", "user", "guest", "root"));
        initSecurityUser();
        for (String uri : createList(asList(UserRouterTable.USER_ID))) {
            callInit(uri);
        }

    }

    @Transactional
    public void initDatabase() {
        System.err.println("Init Database");
        cleanSecurityUser(asList("admin", "superAdmin", "user", "guest", "root"));
        initSecurityUser();
        for (String uri : createList(asList(UserRouterTable.USER_ID))) {
            callInit(uri);
        }

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
