package com.apigateway.api.database.service.domains;

public interface IDefaultDBService {

    boolean loadDefaultDataIntoDatabase();
    boolean deleteAllData();
    boolean deleteAllDefaultData();
    boolean existTable();

}
