package com.apigateway.api.database.service;

import com.apigateway.api.discoveryclient.assets.table.UserRouterTable;
import com.apigateway.api.discoveryclient.service.DiscoveryClientService;
import com.apigateway.security.SecurityUserEntityRepository;
import com.apigateway.security.model.entity.SecurityUserEntity;
import com.apigateway.security.model.entity.UserCollection;
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
public class DatabaseInitService {

    private final DiscoveryClientService clientService;
    private final WebClient.Builder webClient;
    private final SecurityUserEntityRepository securityUserEntityRepository;

    public void initDatabase() {
        System.err.println("Init Database");
        for (String uri : createList(asList(UserRouterTable.USER_ID))) {
            callInit(uri);
        }
        initSecurityUser();
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
