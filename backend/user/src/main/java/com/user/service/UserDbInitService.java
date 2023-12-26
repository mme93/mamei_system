package com.user.service;

import com.user.model.entity.AccountEntity;
import com.user.model.entity.PrivilegesEntity;
import com.user.model.entity_enum.DataPrivileges;
import com.user.model.entity_enum.Role;
import com.user.model.entity_enum.ServicesPrivileges;
import com.user.model.entity_enum.SystemPrivileges;
import com.user.repository.AccountRepository;
import com.user.repository.PrivilegesEntityRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;


@Service
public class UserDbInitService {

    private final String sqlQuery = "SELECT id, username FROM security_user";

    @PersistenceContext
    private EntityManager entityManager;

    private final AccountRepository accountRepository;

    private final PrivilegesEntityRepository privilegesEntityRepository;

    @Autowired
    public UserDbInitService(AccountRepository accountRepository, PrivilegesEntityRepository privilegesEntityRepository) {
        this.accountRepository = accountRepository;
        this.privilegesEntityRepository = privilegesEntityRepository;
    }

    @Transactional
    public boolean initDatabase() {
        cleanAccountWithPrivileges(asList("admin","guest","root","superAdmin","user"));
        createAccounts();
        return true;
    }

    @Transactional
    public void cleanAccountWithPrivileges(List<String>userNameList){
        for(String userName:userNameList){
            Optional<AccountEntity> accountEntityOpt=accountRepository.findByUsername(userName);
            if(accountEntityOpt.isPresent()){
                AccountEntity accountEntity=accountEntityOpt.get();
                privilegesEntityRepository.deleteById(accountEntity.getUserId());
                accountRepository.delete(accountEntity);
            }
        }
    }

    public void createAccounts() {
        Session session = entityManager.unwrap(Session.class);
        NativeQuery<Object[]> query = session.createNativeQuery(sqlQuery);
        List<Object[]> results = query.getResultList();
        for (Object[] result : results) {
            Integer id = (Integer) result[0];
            String username = (String) result[1];
            try {
                if (!accountRepository.existsByUserId(Long.valueOf(id))) {
                    Role role = getRoleForUsername(username);
                    accountRepository.save(new AccountEntity(
                            Long.valueOf(id), username, username, username, "1123123", getEmail(username), role
                    ));
                    privilegesEntityRepository.save(createPrivilegesForAccount(username,Long.valueOf(id)));
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }

    public PrivilegesEntity createPrivilegesForAccount(String username, Long accountId) {
        String adminServicesPrivileges = ServicesPrivileges.ALL.name();
        String guestServicesPrivileges = ServicesPrivileges.DASHBOARD.name();
        String rootServicesPrivileges = ServicesPrivileges.ALL.name();
        String superAdminServicesPrivileges = ServicesPrivileges.ALL.name();
        String userServicesPrivileges = ServicesPrivileges.USER.name() + ", " + ServicesPrivileges.DASHBOARD.name()
                + ", " + ServicesPrivileges.SUDOKU_MANGER.name();

        String adminDataPrivileges = DataPrivileges.DELETE.name() + ", " + DataPrivileges.INSERT.name() +
                ", " + DataPrivileges.SELECT.name() + ", " + DataPrivileges.UPDATE.name();
        String guestDataPrivileges = DataPrivileges.SELECT.name();
        String rootDataPrivileges = DataPrivileges.DELETE.name() + ", " + DataPrivileges.INSERT.name() +
                ", " + DataPrivileges.SELECT.name() + ", " + DataPrivileges.UPDATE.name();
        String superAdminDataPrivileges = DataPrivileges.DELETE.name() + ", " + DataPrivileges.INSERT.name() +
                ", " + DataPrivileges.SELECT.name() + ", " + DataPrivileges.UPDATE.name();
        String userDataPrivileges = DataPrivileges.DELETE.name() + ", " + DataPrivileges.INSERT.name() +
                ", " + DataPrivileges.SELECT.name() + ", " + DataPrivileges.UPDATE.name();

        String adminSystemPrivileges = SystemPrivileges.ALL_READ.name() + ", " + SystemPrivileges.ALL_EXECUTE.name() + ", " + SystemPrivileges.ALL_WRITE.name();
        String guestSystemPrivileges = SystemPrivileges.OWNER_DATA_READ.name();
        String rootSystemPrivileges = SystemPrivileges.ALL_READ.name() + ", " + SystemPrivileges.ALL_EXECUTE.name() + ", " + SystemPrivileges.ALL_WRITE.name();
        String superAdminSystemPrivileges = SystemPrivileges.ALL_READ.name() + ", " + SystemPrivileges.ALL_EXECUTE.name() + ", " + SystemPrivileges.ALL_WRITE.name();
        String userSystemPrivileges = SystemPrivileges.OWNER_DATA_READ.name() + ", " + SystemPrivileges.OWNER_DATA_EXECUTE.name() + ", " + SystemPrivileges.OWNER_DATA_WRITE.name();

        if (username.equals("admin")) {
            return createPrivileges(accountId, adminServicesPrivileges, adminDataPrivileges, adminSystemPrivileges);
        } else if (username.equals("root")) {
            return createPrivileges(accountId, rootServicesPrivileges, rootDataPrivileges, rootSystemPrivileges);
        } else if (username.equals("superAdmin")) {
            return createPrivileges(accountId, superAdminServicesPrivileges, superAdminDataPrivileges, superAdminSystemPrivileges);
        } else if (username.equals("guest")) {
            return createPrivileges(accountId, guestServicesPrivileges, guestDataPrivileges, guestSystemPrivileges);
        } else if (username.equals("user")) {
            return createPrivileges(accountId, userServicesPrivileges, userDataPrivileges, userSystemPrivileges);
        }
        throw new IllegalArgumentException("Invalid username: " + username);
    }

    public PrivilegesEntity createPrivileges(Long accountId, String servicesPrivileges, String dataPrivileges, String systemPrivileges) {
        return new PrivilegesEntity(
                accountId, servicesPrivileges, systemPrivileges, dataPrivileges
        );
    }

    public String getEmail(String username) {
        return username + "@" + username + ".de";
    }

    public Role getRoleForUsername(String userName) {
        switch (userName) {
            case "admin":
            case "root":
            case "superAdmin":
                return Role.ADMIN;
            case "guest":
                return Role.GUEST;
            case "user":
                return Role.USER;
            default:
                throw new IllegalArgumentException("Invalid username: " + userName);
        }
    }

}
