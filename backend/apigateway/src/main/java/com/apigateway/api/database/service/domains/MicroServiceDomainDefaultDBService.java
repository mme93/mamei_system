package com.apigateway.api.database.service.domains;

import org.springframework.stereotype.Service;

@Service
public class MicroServiceDomainDefaultDBService implements IDefaultDBService{
    @Override
    public boolean loadDefaultDataIntoDatabase() {
        return false;
    }

    @Override
    public boolean deleteAllData() {
        return false;
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
