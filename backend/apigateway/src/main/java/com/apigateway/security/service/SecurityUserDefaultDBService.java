package com.apigateway.security.service;

import com.apigateway.security.SecurityUserEntityRepository;
import com.apigateway.security.model.entity.SecurityUserEntity;
import com.apigateway.security.model.entity.UserCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * Service class for managing default database operations related to security users.
 */
@Service
public class SecurityUserDefaultDBService {

    private final SecurityUserEntityRepository securityUserEntityRepository;
    private final UserService userService;

    /**
     * Constructs a new SecurityUserDefaultDBService with the specified repositories.
     * @param securityUserEntityRepository the repository for managing security user entities.
     * @param userService the service for managing user-related operations.
     */
    @Autowired
    public SecurityUserDefaultDBService(SecurityUserEntityRepository securityUserEntityRepository, UserService userService) {
        this.securityUserEntityRepository = securityUserEntityRepository;
        this.userService = userService;
    }

    /**
     * Saves default security user dataset to the database.
     * @return a string containing the usernames of the saved security users.
     */
    public String saveTableSecurityUserDataset() {
        PasswordEncoder pw = new BCryptPasswordEncoder();
        List<String> securityUserList = new ArrayList<>();
        if (!securityUserEntityRepository.existsByUsername("admin")) {
            securityUserEntityRepository.save(new SecurityUserEntity(
                    "admin",
                    pw.encode("test123"),
                    UserCollection.MODULE_ADMIN
            ));
            securityUserList.add("admin");
        }
        if (!securityUserEntityRepository.existsByUsername("superAdmin")) {
            securityUserEntityRepository.save(new SecurityUserEntity(
                    "superAdmin",
                    pw.encode("test123"),
                    UserCollection.SUPER_ADMIN
            ));
            securityUserList.add("superAdmin");
        }
        if (!securityUserEntityRepository.existsByUsername("user")) {
            securityUserEntityRepository.save(new SecurityUserEntity(
                    "user",
                    pw.encode("test123"),
                    UserCollection.USER
            ));
            securityUserList.add("user");
        }
        if (!securityUserEntityRepository.existsByUsername("guest")) {
            securityUserEntityRepository.save(new SecurityUserEntity(
                    "guest",
                    pw.encode("test123"),
                    UserCollection.GUEST
            ));
            securityUserList.add("guest");
        }
        if (!securityUserEntityRepository.existsByUsername("root")) {
            securityUserEntityRepository.save(new SecurityUserEntity(
                    "root",
                    pw.encode("test123"),
                    UserCollection.SYSTEM_ADMIN
            ));
            securityUserList.add("root");
        }
        return listToStringWithDelimiter(securityUserList);
    }

    /**
     * Deletes all security user data from the database.
     * @return a string containing the usernames of the deleted security users.
     */
    public String deleteTableSecurityUserDataset() {
        List<String> securityUserList = new ArrayList<>();
        for(SecurityUserEntity securityUserEntity:securityUserEntityRepository.findAll()){
            securityUserList.add(securityUserEntity.getUsername());
        }
        securityUserEntityRepository.deleteAll();
        return listToStringWithDelimiter(securityUserList);
    }

    /**
     * Deletes only default security user data from the database.
     * @return a string containing the usernames of the deleted default security users.
     */
    public String deleteDefaultDatasetSecurityUserDataset() {
        List<String> securityUserList = new ArrayList<>();
        for (String securityUser : asList("admin", "superAdmin", "user", "guest", "root")) {
            if (securityUserEntityRepository.existsByUsername(securityUser)
            && !securityUser.equals(userService.getCurrentUsername())) {
                securityUserEntityRepository.deleteByUsername(securityUser);
                securityUserList.add(securityUser);
            }
        }
        return listToStringWithDelimiter(securityUserList);
    }

    /**
     * Converts a list of strings into a single string, with elements separated by a delimiter.
     *
     * @param securityUserList the list of strings to be converted
     * @return a single string containing the elements of the list separated by a delimiter
     */
    private String listToStringWithDelimiter(List<String> securityUserList) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < securityUserList.size(); i++) {
            if (i == securityUserList.size() - 1) {
                sb.append(securityUserList.get(i));
            } else {
                sb.append(securityUserList.get(i)).append(", ");
            }
        }
        return sb.toString();
    }
}
