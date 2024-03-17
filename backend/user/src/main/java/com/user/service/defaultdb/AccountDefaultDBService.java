package com.user.service.defaultdb;

import com.user.model.entity.AccountEntity;
import com.user.model.entity.SecurityUserEntity;
import com.user.model.entity_enum.Role;
import com.user.repository.AccountRepository;
import com.user.repository.SecurityUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountDefaultDBService {

    private final AccountRepository accountRepository;
    private final SecurityUserRepository securityUserRepository;

    @Autowired
    public AccountDefaultDBService(AccountRepository accountRepository, SecurityUserRepository securityUserRepository) {
        this.accountRepository = accountRepository;
        this.securityUserRepository = securityUserRepository;
    }

    public void createDefaultDataSet() {
        List<SecurityUserEntity> securityUserEntityList = securityUserRepository.findAll();
        for (SecurityUserEntity securityUserEntity : securityUserEntityList) {
                if (securityUserEntity.getUsername().equals("admin")) {
                    createAccount(securityUserEntity, Role.ADMIN);
                } else if (securityUserEntity.getUsername().equals("superAdmin")) {
                    createAccount(securityUserEntity, Role.ADMIN);
                } else if (securityUserEntity.getUsername().equals("user")) {
                    createAccount(securityUserEntity, Role.USER);
                } else if (securityUserEntity.getUsername().equals("guest")) {
                    createAccount(securityUserEntity, Role.GUEST);
                } else if (securityUserEntity.getUsername().equals("root")) {
                    createAccount(securityUserEntity, Role.SYSTEM);
                }
            }

    }

    private void createAccount(SecurityUserEntity securityUserEntity,Role role) {
        if (!accountRepository.existsByUserId(Long.valueOf(securityUserEntity.getId()))) {
            accountRepository.save(new AccountEntity(
                    Long.valueOf(
                            securityUserEntity.getId()),
                    securityUserEntity.getUsername(),
                    securityUserEntity.getUsername(),
                    securityUserEntity.getUsername(),
                    "0201/2147899",
                    getEmail(securityUserEntity.getUsername()),
                    role
            ));
        }
    }

    private String getEmail(String username) {
        return username + "@test-mail.de";
    }

    public void deleteAllData() {
        accountRepository.deleteAll();
    }

    public void deleteDefaultDataset() {
        List<SecurityUserEntity> securityUserEntityList = securityUserRepository.findAll();
        for (SecurityUserEntity securityUserEntity : securityUserEntityList) {
            if (securityUserEntity.getUsername().equals("admin")) {
                removeAccount(securityUserEntity.getId());
            } else if (securityUserEntity.getUsername().equals("superAdmin")) {
                removeAccount(securityUserEntity.getId());
            } else if (securityUserEntity.getUsername().equals("user")) {
                removeAccount(securityUserEntity.getId());
            } else if (securityUserEntity.getUsername().equals("guest")) {
                removeAccount(securityUserEntity.getId());
            } else if (securityUserEntity.getUsername().equals("root")) {
                removeAccount(securityUserEntity.getId());
            }
        }
    }

    private void removeAccount(int id) {
        Optional<AccountEntity> accountOpt = accountRepository.findByUserId(Long.valueOf(id));
        if (accountOpt.isPresent()) {
            accountRepository.delete(accountOpt.get());
        }
    }
}
