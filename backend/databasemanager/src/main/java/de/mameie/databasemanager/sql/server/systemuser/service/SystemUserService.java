package de.mameie.databasemanager.sql.server.systemuser.service;

import de.mameie.databasemanager.sql.executor.systemuser.administration.AdministrationSystemUserSqlExecutor;
import de.mameie.databasemanager.sql.server.systemuser.model.SystemUser;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class SystemUserService {


    public List<SystemUser> getAllSystemUsers(String server) throws SQLException {
        return AdministrationSystemUserSqlExecutor
                .builder()
                .withServerName(server)
                .build()
                .getAllSystemUser();
    }
}
